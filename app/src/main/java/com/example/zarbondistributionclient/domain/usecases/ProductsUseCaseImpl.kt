package com.example.zarbondistributionclient.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.zarbondistributionclient.data.models.productsmodel.ProductData
import com.example.zarbondistributionclient.domain.repositories.ProductsRepository
import com.example.zarbondistributionclient.domain.repositories.ProductsRepositoryIml
import kotlinx.coroutines.flow.collect

class ProductsUseCaseImpl : ProductsUseCase {
    private val repository: ProductsRepository = ProductsRepositoryIml()
    override val errorProductsLiveData = MutableLiveData<Unit>()
    override fun getProducts(categoryId: String): LiveData<List<ProductData>> = liveData {
        repository.getProducts(categoryId).collect {
            if (it.isSuccess) {
                emit(it.getOrNull()!!)
            } else {
                errorProductsLiveData.postValue(Unit)
            }
        }
    }
}