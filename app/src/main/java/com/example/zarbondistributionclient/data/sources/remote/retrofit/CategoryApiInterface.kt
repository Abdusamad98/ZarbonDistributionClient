package com.example.zarbondistributionclient.data.sources.remote.retrofit

import com.example.zarbondistributionclient.data.models.categorymodel.CategoryData
import retrofit2.Response
import retrofit2.http.GET


interface CategoryApiInterface {

    @GET("product/category-list/")
    suspend fun getCategories(
        //  @Header("Authorization") token: String
    ): Response<List<CategoryData>>

}