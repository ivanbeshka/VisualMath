package com.example.visualmath.view.learn

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.visualmath.R

class SampleFragment(
    private val topNum: String,
    private val bottomNum: String,
    private val resultNum: String,
    private val desc: String,
    private val abacus: Drawable
) : Fragment() {

    private lateinit var tvTopNum: TextView
    private lateinit var tvBottomNum: TextView
    private lateinit var tvResultNum: TextView
    private lateinit var tvDesc: TextView
    private lateinit var ivAbacus: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sample, container, false)

        initView(view)

        tvTopNum.text = topNum
        tvBottomNum.text = bottomNum
        tvResultNum.text = resultNum
        tvDesc.text = desc
        ivAbacus.setImageDrawable(abacus)

        return view
    }

    private fun initView(view: View) {
        tvTopNum = view.findViewById(R.id.tv_top_num)
        tvBottomNum = view.findViewById(R.id.tv_bottom_num)
        tvResultNum = view.findViewById(R.id.tv_result_num)
        tvDesc = view.findViewById(R.id.tv_desc)
        ivAbacus = view.findViewById(R.id.abacusView)
    }
}