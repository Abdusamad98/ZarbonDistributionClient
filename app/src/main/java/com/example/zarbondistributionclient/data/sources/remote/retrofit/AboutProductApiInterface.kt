package com.example.zarbondistributionclient.data.sources.remote.retrofit

import com.example.zarbondistributionclient.data.models.productsmodel.AboutProduct
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface AboutProductApiInterface {
    @GET
    suspend fun getAboutProduct(
        @Url url: String
        //  @Header("Authorization") token: String
    ): Response<AboutProduct>

}