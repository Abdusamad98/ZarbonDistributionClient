package com.example.zarbondistributionclient.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReportFragmentViewModel : ViewModel() {

//    private val productsUseCase: ProductsUseCase = ProductsUseCaseImpl()
//    val errorProductsLiveData: LiveData<Unit> = productsUseCase.errorProductsLiveData
//    val progressProductsLiveData = MutableLiveData<Boolean>()
//    val connectionErrorProductsLiveData = MutableLiveData<Unit>()
//    val successProductsLiveData = MediatorLiveData<List<ProductData>>()
//
//    init {
//        getCategories()
//    }
//
//    fun getProducts(categoryId: Int) {
//        if (isConnected()) {
//            progressProductsLiveData.value = true
//            val liveData = productsUseCase.getProducts(categoryId.toString())
//            successProductsLiveData.addSource(liveData) {
//                progressProductsLiveData.value = false
//                successProductsLiveData.value = it
//                successProductsLiveData.removeSource(liveData)
//            }
//        } else {
//            // progressLiveData.value = false
//            connectionErrorProductsLiveData.value = Unit
//        }
//    }

    val closeLiveData = MutableLiveData<Unit>()
    fun closeSearch() {
        closeLiveData.value = Unit
    }
}