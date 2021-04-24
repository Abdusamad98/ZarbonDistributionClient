package com.example.zarbondistributionclient.domain.repositories

import android.util.Log
import com.example.zarbondistributionclient.data.models.productsmodel.AboutProduct
import com.example.zarbondistributionclient.data.sources.remote.retrofit.AboutProductApiInterface
import com.example.zarbondistributionclient.data.sources.remote.retrofit.ApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AboutProductRepositoryImpl : AboutProductRepository {
    private val api = ApiClient.retrofit.create(AboutProductApiInterface::class.java)
    override suspend fun getAboutProduct(id: Int):
            Flow<Result<Pair<Int, AboutProduct?>>> = flow {
        try {
            val response = api.getAboutProduct("product/product-with-presentation/${id}/")
            val code = response.code()
            if (code == 200) {
                emit(Result.success(Pair(200, response.body())))
            } else if (code == 500 || code == 400 || code == 404) {
                emit(Result.success(Pair(404, null)))
            }
        } catch (e: Exception) {
            Log.d("SELL", "exception = $e")
        }
    }
}