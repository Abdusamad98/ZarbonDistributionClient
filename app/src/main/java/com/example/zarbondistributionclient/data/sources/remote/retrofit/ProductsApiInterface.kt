package com.example.zarbondistributionclient.data.sources.remote.retrofit

import com.example.zarbondistributionclient.data.models.productsmodel.ProductData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductsApiInterface {
    @GET
    suspend fun getProducts(
        @Url url: String
        //  @Header("Authorization") token: String
    ): Response<List<ProductData>>
}