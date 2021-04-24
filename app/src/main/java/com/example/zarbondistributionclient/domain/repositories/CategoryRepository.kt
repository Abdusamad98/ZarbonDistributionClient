package com.example.zarbondistributionclient.domain.repositories

import com.example.zarbondistributionclient.data.models.categorymodel.CategoryData
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<Result<List<CategoryData>?>>
}