package com.example.visualmath.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.visualmath.R
import com.google.android.material.button.MaterialButton

class MainMenuFragment : Fragment() {

    private lateinit var btnStart: MaterialButton
    private lateinit var btnContinue: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_menu, container, false)

        btnStart = view.findViewById(R.id.btn_start)
        btnContinue = view.findViewById(R.id.btn_continue)

        btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_themesFragment)
        }

        btnContinue.setOnClickListener {
            //todo navigate to last lesson
        }

        return view
    }
}