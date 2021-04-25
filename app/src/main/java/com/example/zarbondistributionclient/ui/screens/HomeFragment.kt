package com.example.zarbondistributionclient.ui.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.zarbondistributionclient.R
import com.example.zarbondistributionclient.data.sources.local.SAVER
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clientName.text = SAVER.getLogin()

        logOut.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Diqqat!")
                .setMessage("Haqiqatdan tizimdan chiqmoqchimisiz!")
                .setNegativeButton("Yo'q") { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton("Ha") { dialog, _ ->
                    SAVER.setClientId(0)
                    SAVER.setAgentId(0)
                    SAVER.setLogin("")
                    SAVER.setPhoneNumber("")
                    dialog.cancel()
                    findNavController().navigateUp()
                }.show()
        }

        shoppingCart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_shoppedProductsFragment)
        }

        button1.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_reportFragment)
        }

        button2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_sellFragment)
        }

        button3.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_suggestionsFragment)
        }

        button4.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_buyFragment)
        }
    }
}