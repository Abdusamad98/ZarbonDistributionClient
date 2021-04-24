package com.example.zarbondistributionclient.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.zarbondistributionclient.R
import com.example.zarbondistributionclient.ui.dialogs.CarChooseDialog
import kotlinx.android.synthetic.main.suggest_fragment.*

class SuggestionsFragment : Fragment(R.layout.suggest_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carStamp.setOnClickListener {
            carChosenChooseDialog(getList())
        }

    }

    private fun getList(): List<String> {
        var list = listOf<String>(
            "Chevrolet",
            "Daewoo",
            "Honda",
            "Ford",
            "Flat",
            "Toyota",
            "Mercedes Benz"
        )
        return list
    }

    private fun carChosenChooseDialog(data: List<String>) {
        val dialog = CarChooseDialog(requireContext(), data)
        dialog.show()
        dialog.setOnClientChosen { name ->
            carStamp.text = name
        }
    }

}