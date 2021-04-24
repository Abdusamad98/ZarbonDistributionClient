package com.example.zarbondistributionclient.domain.repositories

import com.example.zarbondistributionclient.data.models.categorymodel.CategoryData
import com.example.zarbondistributionclient.data.sources.remote.retrofit.ApiClient
import com.example.zarbondistributionclient.data.sources.remote.retrofit.CategoryApiInterface
import com.example.zarbondistributionclient.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryRepositoryImpl : CategoryRepository {

    private val api = ApiClient.retrofit.create(CategoryApiInterface::class.java)
    override suspend fun getCategories(): Flow<Result<List<CategoryData>?>> = flow {
        try {
            val response = api.getCategories()
            if (response.code() == 200) {
                emit(Result.success(response.body()))
            }

        } catch (e: Exception) {
            //   emit(Result.failure(e))
            log("TTT", "exception = $e" + "Xato!")
        }
    }
}