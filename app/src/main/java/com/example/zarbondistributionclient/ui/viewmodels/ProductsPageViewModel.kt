package com.example.zarbondistributionclient.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zarbondistributionclient.data.models.buymodel.BuyProductData
import com.example.zarbondistributionclient.data.models.buymodel.BuyProductResponse
import com.example.zarbondistributionclient.data.models.categorymodel.CategoryData
import com.example.zarbondistributionclient.data.models.productsmodel.ProductData
import com.example.zarbondistributionclient.domain.usecases.*
import com.example.zarbondistributionclient.utils.isConnected

class ProductsPageViewModel : ViewModel() {

    private val categoriesUseCase: CategoriesUseCase = CategoriesUseCaseImpl()
    val errorCategoriesLiveData: LiveData<Unit> = categoriesUseCase.errorCategoriesLiveData
    val progressCategoriesLiveData = MutableLiveData<Boolean>()
    val connectionErrorCategoriesLiveData = MutableLiveData<Unit>()
    val successCategoriesLiveData = MediatorLiveData<List<CategoryData>>()


    private val productsUseCase: ProductsUseCase = ProductsUseCaseImpl()
    val errorProductsLiveData: LiveData<Unit> = productsUseCase.errorProductsLiveData
    val progressProductsLiveData = MutableLiveData<Boolean>()
    val connectionErrorProductsLiveData = MutableLiveData<Unit>()
    val successProductsLiveData = MediatorLiveData<List<ProductData>>()



    private val useCaseBuy: BuyProductUseCase = BuyProductUseCaseImpl()
    val errorNotResponseBuyLiveData: LiveData<String> = useCaseBuy.errorNotResponseLiveData
    val errorResponseBuyLiveData = useCaseBuy.errorResponseLiveData
    val progressSellLiveData = MutableLiveData<Boolean>()
    val connectionBuyError = MutableLiveData<Unit>()
    val successBuyLiveData = MediatorLiveData<BuyProductResponse>()


    fun buyProduct(productData: BuyProductData) {
        if (isConnected()) {
            progressSellLiveData.value = true
            val lvd = useCaseBuy.buyProduct(productData)
            successBuyLiveData.addSource(lvd) {
                progressSellLiveData.value = false
                successBuyLiveData.value = it
                successBuyLiveData.removeSource(lvd)
            }
        } else {
            connectionBuyError.value = Unit
        }

    }


    fun getProducts(categoryId: Int) {
        if (isConnected()) {
            progressProductsLiveData.value = true
            val liveData = productsUseCase.getProducts(categoryId.toString())
            successProductsLiveData.addSource(liveData) {
                progressProductsLiveData.value = false
                successProductsLiveData.value = it
                successProductsLiveData.removeSource(liveData)
            }
        } else {
            // progressLiveData.value = false
            connectionErrorProductsLiveData.value = Unit
        }
    }

    fun getCategories() {
        if (isConnected()) {
            progressCategoriesLiveData.value = true
            val liveData = categoriesUseCase.getCategories()
            successCategoriesLiveData.addSource(liveData) {
                progressCategoriesLiveData.value = false
                successCategoriesLiveData.value = it
                successCategoriesLiveData.removeSource(liveData)
            }
        } else {
            connectionErrorCategoriesLiveData.value = Unit
        }

    }

    val closeLiveData = MutableLiveData<Unit>()
    fun closeSearch() {
        closeLiveData.value = Unit
    }
}