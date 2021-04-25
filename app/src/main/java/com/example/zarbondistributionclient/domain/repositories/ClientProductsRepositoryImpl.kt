package com.example.zarbondistributionclient.domain.repositories

import com.example.zarbondistributionclient.data.models.clientproducts.ClientProducts
import com.example.zarbondistributionclient.data.sources.remote.retrofit.ApiClient
import com.example.zarbondistributionclient.data.sources.remote.retrofit.ClientProductsApi
import com.example.zarbondistributionclient.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ClientProductsRepositoryImpl : ClientProductsRepository {
    private val api = ApiClient.retrofit.create(ClientProductsApi::class.java)
    override suspend fun getClientProducts(clientId: String): Flow<Result<List<ClientProducts>?>> =
        flow {

            try {
                val response = api.getClientProducts("order/client-sell-order-list/${clientId}/")
                if (response.code() == 200) {
                    emit(Result.success(response.body()))
                }
            } catch (e: Exception) {
                emit(Result.failure(e))
                log("TTT", "exception = $e" + "Xatolik!")
            }
        }


}