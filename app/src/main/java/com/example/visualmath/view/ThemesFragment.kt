package com.example.visualmath.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.visualmath.R
import com.google.android.material.button.MaterialButton

class ThemesFragment : Fragment() {

    private lateinit var btnKnowing: MaterialButton
    private lateinit var btnPlus: MaterialButton
    private lateinit var btnMinus: MaterialButton
    private lateinit var btnMultiply: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBar()
    }

    private fun setActionBar() {
        setHasOptionsMenu(true)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.show()
        actionBar?.title = "Темы"
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_themes, container, false)

        initView(view)
        setClickListeners()

        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            (activity as AppCompatActivity).supportActionBar?.hide()
            activity?.onBackPressed()
            return true
        }
        return false
    }

    private fun initView(view: View) {
        btnKnowing = view.findViewById(R.id.btn_knowing)
        btnMinus = view.findViewById(R.id.btn_minus)
        btnPlus = view.findViewById(R.id.btn_plus)
        btnMultiply = view.findViewById(R.id.btn_multiply)
    }

    private fun setClickListeners() {
        btnKnowing.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(typeKey, sample)
            findNavController().navigate(R.id.learnContainer, bundle)
        }

        btnMultiply.setOnClickListener {

        }

        btnPlus.setOnClickListener {
            findNavController().navigate(R.id.harderPlusMinusFragment)
        }

        btnMinus.setOnClickListener {
//            findNavController().navigate(R.id.harderPlusMinusFragment)
        }
    }
}