package com.example.zarbondistributionclient.data.sources.remote.retrofit

import com.example.zarbondistributionclient.data.models.buymodel.BuyProductResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface BuyProductApiInterface {
    @FormUrlEncoded
    @POST("order/sell-order-list/")
    suspend fun sellProduct(
        @Header("Accept") app_json: String,
        @Field("order_method") order_method: String,
        @Field("quantity") quantity: String,
        @Field("client") client: Int,
        @Field("product") product: Int,
        @Field("price") price: Int,
    ): Response<BuyProductResponse>
}

