package com.example.visualmath.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.visualmath.R
import com.google.android.material.button.MaterialButton

class HarderPlusMinusFragment : Fragment() {

    private lateinit var btn1ZnNumbers: MaterialButton
    private lateinit var btn2ZnNumbers: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
            return true
        }
        return false
    }

    private fun setActionBar() {
        setHasOptionsMenu(true)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.show()
        actionBar?.title = "Сложение"
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_harder_plus_minus, container, false)

        btn1ZnNumbers = view.findViewById(R.id.btn_1zn_num)
        btn2ZnNumbers = view.findViewById(R.id.btn_2zn_num)

        btn1ZnNumbers.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(typeKey, _1zn)
            findNavController().navigate(R.id.learnContainer, bundle)
        }

        btn2ZnNumbers.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(typeKey, _2zn)
            findNavController().navigate(R.id.learnContainer, bundle)
        }

        return view
    }
}