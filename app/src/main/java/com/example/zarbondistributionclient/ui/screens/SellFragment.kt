package com.example.zarbondistributionclient.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.zarbondistributionclient.R
import kotlinx.android.synthetic.main.sell_fragment.*

class SellFragment : Fragment(R.layout.sell_fragment){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        backToHomeSellProducts.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}