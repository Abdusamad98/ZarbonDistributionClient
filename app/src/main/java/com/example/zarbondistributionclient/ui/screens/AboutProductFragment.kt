package com.example.zarbondistributionclient.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zarbondistributionclient.R
import com.example.zarbondistributionclient.data.models.productsmodel.AboutProduct
import com.example.zarbondistributionclient.data.models.productsmodel.Image
import com.example.zarbondistributionclient.ui.adapters.AboutProductAdapter
import com.example.zarbondistributionclient.ui.viewmodels.GetAboutProductViewModel
import com.example.zarbondistributionclient.utils.showToast
import kotlinx.android.synthetic.main.fragment_about_product.*

class AboutProductFragment : Fragment(R.layout.fragment_about_product) {

    private val viewModel: GetAboutProductViewModel by viewModels()
    private val aboutAdapter by lazy { AboutProductAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelSetUp()

        val args = AboutProductFragmentArgs.fromBundle(requireArguments())
        if (args.productId != 0) viewModel.getAboutProduct(args.productId)

        backToHomeAboutProducts.setOnClickListener {
            findNavController().navigateUp()
        }
    }


    private val progressAboutObserver = Observer<Boolean> {
        if (it) {
            productsAboutProgressBar.visibility = View.VISIBLE
        } else {
            productsAboutProgressBar.visibility = View.GONE
        }
    }


    private val errorAboutObserver = Observer<String> {
        requireActivity().showToast("Ulanishda xatolik!")

    }
    private val connectionErrorAboutObserver = Observer<Unit> {
        requireActivity().showToast("Internet yuq!")
    }
    private val successAboutProductObserver = Observer<AboutProduct> { response ->
        initRecycler(response.images)
        aboutText.text = response.product_description.description
    }

    private fun initRecycler(aboutList: List<Image>) {
        aboutAdapter.submitList(aboutList)
        aboutProducts.layoutManager = LinearLayoutManager(requireContext())
        aboutProducts.adapter = aboutAdapter
    }


    private fun viewModelSetUp() {
        viewModel.progressAboutProductLiveData.observe(viewLifecycleOwner, progressAboutObserver)
        viewModel.errorResponseLiveData.observe(viewLifecycleOwner, errorAboutObserver)
        viewModel.connectionErrorLiveData.observe(viewLifecycleOwner, connectionErrorAboutObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner, successAboutProductObserver)
    }
}