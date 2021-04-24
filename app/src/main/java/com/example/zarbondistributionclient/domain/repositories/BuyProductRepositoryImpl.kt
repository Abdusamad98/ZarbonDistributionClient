package com.example.zarbondistributionclient.domain.repositories

import android.util.Log
import com.example.zarbondistributionclient.data.models.buymodel.BuyProductData
import com.example.zarbondistributionclient.data.models.buymodel.BuyProductResponse
import com.example.zarbondistributionclient.data.sources.remote.retrofit.ApiClient
import com.example.zarbondistributionclient.data.sources.remote.retrofit.BuyProductApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BuyProductRepositoryImpl : BuyProductRepository {
    private val api = ApiClient.retrofit.create(BuyProductApiInterface::class.java)

    override suspend fun buyProduct(productData: BuyProductData):
            Flow<Result<Pair<Int, BuyProductResponse?>>> = flow {
        try {
            val response = api.sellProduct(
                "application/json",
                "client",
                productData.quantity,
                productData.client,
                productData.product,
                productData.price
            )
            if (response.code() == 201) {
                emit(Result.success(Pair(201, response.body())))
            } else if (response.code() == 400) {
                emit(Result.success(Pair(400, null)))
            }
        } catch (e: Exception) {
            Log.d("SELL", "exception = $e")
        }
    }
}