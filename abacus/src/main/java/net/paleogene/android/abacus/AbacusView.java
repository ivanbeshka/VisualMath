package net.paleogene.android.abacus;

import java.util.concurrent.Semaphore;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.RequiresApi;

import static android.content.ContentValues.TAG;

/**
 * View that draws, handles touch events for a visual abacus.
 */
public class AbacusView extends SurfaceView
        implements SurfaceHolder.Callback {

    class DrawThread extends Thread {

        private Semaphore sem = new Semaphore(10);

        private boolean isPaused = false;

        public DrawThread(SurfaceHolder surfaceHolder) {
            mSurfaceHolder = surfaceHolder;
        }

        /**
         * Politely ask the thread to stop running.
         */
        public void stopDrawing() {
            interrupt();
        }

        /**
         * Pause the drawing thread by acquiring the internal draw mutex.
         * Only a single thread should be allowed to call either this method
         * or resumeDrawing().
         *
         * @throws InterruptedException
         */
        public void pauseDrawing()
                throws InterruptedException {
            if (!isPaused) {
                sem.acquire();
                isPaused = true;
            }
        }

        /**
         * Allow the drawing thread to resume by releasing the internal draw
         * mutex.  Only a single thread should be allowed to call either this
         * method or pauseDrawing().
         */
        public void resumeDrawing() {
            if (isPaused) {
                sem.release();
                isPaused = false;
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void run() {

            Canvas c = null;
            try {
                c = mSurfaceHolder.lockCanvas(null);
                synchronized (mSurfaceHolder) {
                    doDraw(c);
                }
            } finally {
                if (c != null) mSurfaceHolder.unlockCanvasAndPost(c);
            }

            while (!isInterrupted()) {
                try {
                    sem.acquire();
                } catch (InterruptedException e) {
                    return;
                }
                sem.release();
            }
        }
    }

    /**
     * Handle to the surface manager object we interact with
     */
    private SurfaceHolder mSurfaceHolder;

    private DrawThread thread;

    private AbacusEngine engine;

    private RowEngine motionRow = null;
    private int motionBead = -1;

    private int mCanvasWidth = 1;
    private int mCanvasHeight = 1;

    private Context context;

    private AbacusEngine.BeadState beadState;

    public AbacusView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        // Register interest in changes to the surface
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void doDraw(Canvas canvas) {

        canvas.drawColor(context.getColor(R.color.background));

        if (engine != null) {
            canvas.rotate(-90, engine.getWidth() / 2, engine.getHeight() / 2);
            engine.draw(canvas);
        }
    }

    private void showReadout() {
        if (Abacus.readout != null) {
            int result = engine.getValue();
            if (result > -1)
                Abacus.readout.setText(Integer.toString(result));
            else
                Abacus.readout.setText("?");
        }
    }

    public void saveState(Bundle state) {
        beadState = engine.getState();
        state.putSerializable("beadState", beadState);
    }

    public void restoreState(Bundle state) {
        beadState = (AbacusEngine.BeadState) state.getSerializable("beadState");
        engine.setState(beadState);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        mCanvasWidth = width;
        mCanvasHeight = height;
        engine = new AbacusEngine(mCanvasWidth, mCanvasHeight, 10, context);
        if (beadState != null)
            engine.setState(beadState);

        showReadout();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (thread == null || thread.getState() == Thread.State.TERMINATED)
            thread = new DrawThread(holder);

        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Wait until the thread has really shut down, otherwise it might touch
        // the surface after we return and explode...
        boolean retry = true;
        thread.stopDrawing();
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if (motionBead > -1) {
                    synchronized (mSurfaceHolder) {
                        motionRow.moveBeadTo(motionBead, (int) event.getX());
                    }
                } else {
                    int x, y;

                    /* Process historical events so that beads don't "slip" */
                    /* TODO Try interpolating between such events too... */
                    for (int i = 0; i < event.getHistorySize(); i++) {
                        x = (int) event.getHistoricalY(i);
                        y = (int) event.getHistoricalX(i);

                        motionRow = engine.getRowAt(x, y);
                        if (motionRow != null) {
                            motionBead = motionRow.getBeadAt(x, y);
                            if (motionBead > -1) {
                                thread.resumeDrawing();
                                break;
                            }
                        }
                    }

                    if (motionBead == -1) {
                        x = (int) event.getX();
                        y = (int) event.getY();
                        motionRow = engine.getRowAt(x, y);
                        if (motionRow != null) {
                            motionBead = motionRow.getBeadAt(x, y);
                            if (motionBead > -1)
                                thread.resumeDrawing();
                        }
                    }
                }
                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (motionBead > -1) showReadout();
                motionRow = null;
                motionBead = -1;
                try {
                    thread.pauseDrawing();
                } catch (InterruptedException e) {
                }
                return true;

            default:
                return super.onTouchEvent(event);
        }
    }

}
