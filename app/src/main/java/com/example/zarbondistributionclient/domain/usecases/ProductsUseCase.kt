package com.example.zarbondistributionclient.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionclient.data.models.productsmodel.ProductData

interface ProductsUseCase {
    val errorProductsLiveData: LiveData<Unit>
    fun getProducts(categoryId: String): LiveData<List<ProductData>>
}