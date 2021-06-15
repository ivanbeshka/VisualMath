package com.example.visualmath.view.learn

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.visualmath.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LearnQuizFragment(
    private val desc: String,
    private val abacus: Drawable,
    private val btnTopText: String? = null,
    private val btnBottomText: String? = null,
    private val answer: String? = null,
    private val textBottomInput: String? = null,
    private val isVisibleCat: Boolean = false
) : Fragment() {

    private lateinit var tvDesc: TextView
    private lateinit var btnTop: MaterialButton
    private lateinit var btnBottom: MaterialButton
    private lateinit var tvInputHint: TextView
    private lateinit var ivCat: ImageView
    private lateinit var editText: TextInputEditText
    private lateinit var etLayout: TextInputLayout
    private lateinit var ivAbacus: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_learn_quiz, container, false)

        initView(view)

        initNeededView()

        return view
    }

    private fun initNeededView() {
        tvDesc.text = desc
        ivAbacus.setImageDrawable(abacus)

        if (btnTopText != null) {
            btnTop.visibility = View.VISIBLE
            btnTop.text = btnTopText
            btnTop.setOnClickListener {
                //todo
            }
        } else {
            btnTop.visibility = View.GONE
        }

        if (btnBottomText != null) {
            btnBottom.visibility = View.VISIBLE
            btnBottom.text = btnBottomText
            btnBottom.setOnClickListener {
                //todo
            }
        } else {
            btnBottom.visibility = View.GONE
        }

        if (answer != null) {
            etLayout.visibility = View.VISIBLE
            editText.visibility = View.VISIBLE
            if (textBottomInput != null) {
                tvInputHint.visibility = View.VISIBLE
                tvInputHint.text = textBottomInput
            } else {
                tvInputHint.visibility = View.GONE
            }

            editText.addTextChangedListener { text: Editable? ->
                if (text != null && answer == text.toString()) {
                    Toast.makeText(context, "Правильно!", Toast.LENGTH_LONG).show()
                }
            }

        } else {
            etLayout.visibility = View.GONE
            editText.visibility = View.GONE
            tvInputHint.visibility = View.GONE
        }

        if (isVisibleCat) {
            ivCat.visibility = View.VISIBLE
        } else {
            ivCat.visibility = View.GONE
        }
    }

    private fun initView(view: View) {
        tvDesc = view.findViewById(R.id.tv_desc_main)
        btnTop = view.findViewById(R.id.btn_top)
        btnBottom = view.findViewById(R.id.btn_bottom)
        tvInputHint = view.findViewById(R.id.tv_answer_hint)
        ivCat = view.findViewById(R.id.iv_cat)
        editText = view.findViewById(R.id.et_answer)
        etLayout = view.findViewById(R.id.et_layout)
        ivAbacus = view.findViewById(R.id.abacusView2)
    }
}