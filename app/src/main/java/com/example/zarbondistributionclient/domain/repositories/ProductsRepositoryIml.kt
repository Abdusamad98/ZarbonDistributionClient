package com.example.zarbondistributionclient.domain.repositories

import com.example.zarbondistributionclient.data.models.productsmodel.ProductData
import com.example.zarbondistributionclient.data.sources.remote.retrofit.ApiClient
import com.example.zarbondistributionclient.data.sources.remote.retrofit.ProductsApiInterface
import com.example.zarbondistributionclient.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductsRepositoryIml : ProductsRepository {
    private val api = ApiClient.retrofit.create(ProductsApiInterface::class.java)
    override suspend fun getProducts(categoryId: String): Flow<Result<List<ProductData>?>> = flow {

        try {
            val response = api.getProducts("product/category-product/${categoryId}/")
            if (response.code() == 200) {
                emit(Result.success(response.body()))
                log(response.body().toString(), "QQQ")
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
            log("TTT", "exception = $e" + "Xatolik!")
        }
    }


}