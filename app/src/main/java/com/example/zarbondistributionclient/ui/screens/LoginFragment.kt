package com.example.zarbondistributionclient.ui.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.zarbondistributionclient.R
import com.example.zarbondistributionclient.data.models.loginmodel.LoginResponse
import com.example.zarbondistributionclient.data.sources.local.SAVER
import com.example.zarbondistributionclient.ui.viewmodels.LoginViewModel
import com.example.zarbondistributionclient.utils.log
import com.example.zarbondistributionclient.utils.showToast
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment(R.layout.login_fragment) {
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(SAVER.getLogin()!!.isNotEmpty()){
            input_login.setText(SAVER.getLogin())
            loginLayout.visibility = View.GONE
            login.text = "Kirish"
        }

        login.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//            val password = input_parol.text.toString()
//            val username = input_login.text.toString()
//
//            if (username.isEmpty()) {
//                input_login.error = "Loginni kiriting!"
//            } else if (password.isEmpty()) {
//                input_parol.error = "Parolni kiriting!"
//            } else if (SAVER.token.isEmpty()) {
//                viewModel.login(username, password)
//            }
//            else if(SAVER.getPassword() == input_parol.text.toString() ) {
//                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//            }
//
//            else {
//                requireActivity().showToast("Parol xato!")
//            }

        }
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
        viewModel.errorLoginLiveData.observe(viewLifecycleOwner, errorLoginObserver)
        viewModel.connectionErrorLiveData.observe(viewLifecycleOwner, connectionErrorObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner, successObserver)
    }


    private val progressObserver = Observer<Boolean> {
        if (it) {
            loginProgressBar.visibility = View.VISIBLE
        } else {
            loginProgressBar.visibility = View.GONE
        }
    }

    private val errorLoginObserver = Observer<String> {
        requireActivity().showToast("Parol yoki Login xato!")
    }
    private val connectionErrorObserver = Observer<Unit> {
        requireActivity().showToast("Internet yuq!")
        log("connection error")
    }

    private val successObserver = Observer<LoginResponse> {
        AlertDialog.Builder(requireContext())
            .setTitle("Diqqat!")
            .setMessage("Tizimga kirdingiz !")
            .setPositiveButton("Ok") { dialog, _ ->
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                dialog.cancel()
            }.show()

        log("Success")
    }
}