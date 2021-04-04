package com.example.zarbondistributionclient.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.zarbondistributionclient.R
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button1.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_reportFragment)
        }

        button2.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_sellFragment)
        }

        button3.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_suggestionsFragment)
        }

        button4.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_buyFragment)
        }
    }
}