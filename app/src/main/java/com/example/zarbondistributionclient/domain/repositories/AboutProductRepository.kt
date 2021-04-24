package com.example.zarbondistributionclient.domain.repositories

import com.example.zarbondistributionclient.data.models.productsmodel.AboutProduct
import kotlinx.coroutines.flow.Flow

interface AboutProductRepository {
    suspend fun getAboutProduct(id: Int)
            : Flow<Result<Pair<Int, AboutProduct?>>>
}