package com.example.zarbondistributionclient.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zarbondistributionclient.R
import com.example.zarbondistributionclient.data.models.clientproducts.ClientProducts
import com.example.zarbondistributionclient.data.sources.local.SAVER
import com.example.zarbondistributionclient.ui.adapters.ClientProductListAdapter
import com.example.zarbondistributionclient.ui.viewmodels.ClientProductsViewModel
import com.example.zarbondistributionclient.utils.showToast
import kotlinx.android.synthetic.main.shopped_products_view.*


class ShoppedProductsFragment : Fragment(R.layout.shopped_products_view) {

    private val productsAdapter by lazy { ClientProductListAdapter() }
    private val clientsViewModel: ClientProductsViewModel by viewModels()
    var productData: List<ClientProducts> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clientsViewModel.getClientProducts(SAVER.getClientId())
        productsSetUp()

        backToHomeClientProducts.setOnClickListener {
            findNavController().navigateUp()
        }

    }


    private val progressObserver = Observer<Boolean> {
        if (it) {
            clientProductsProgressBar.visibility = View.VISIBLE
        } else {
            clientProductsProgressBar.visibility = View.GONE
        }
    }


    fun productsSetUp() {
        clientsViewModel.progressProductsLiveData.observe(viewLifecycleOwner, progressObserver)
        clientsViewModel.errorProductsLiveData.observe(viewLifecycleOwner, errorProductsObserver)
        clientsViewModel.successProductsLiveData.observe(
            viewLifecycleOwner,
            successProductsObserver
        )
    }


    private val errorProductsObserver = Observer<Unit> {
        requireActivity().showToast("Ulanishda xatolik!")
    }

    private val successProductsObserver = Observer<List<ClientProducts>> { products ->
        productData = products
        initProductsList(products)
    }

    fun initProductsList(data: List<ClientProducts>) {
        productsAdapter.submitList(data)
        recyclerClientProducts.layoutManager = LinearLayoutManager(requireContext())
        recyclerClientProducts.adapter = productsAdapter
    }
}