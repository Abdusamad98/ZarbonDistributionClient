package com.example.zarbondistributionclient.data.sources.remote.retrofit

import com.example.zarbondistributionclient.data.models.clientproducts.ClientProducts
import retrofit2.http.GET
import retrofit2.http.Url
import retrofit2.Response
interface ClientProductsApi {
    @GET
    suspend fun getClientProducts(
        @Url url: String,
    ): Response<List<ClientProducts>>
}