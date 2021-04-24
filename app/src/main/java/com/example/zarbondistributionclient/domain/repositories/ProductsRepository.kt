package com.example.zarbondistributionclient.domain.repositories

import com.example.zarbondistributionclient.data.models.productsmodel.ProductData
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(categoryId: String): Flow<Result<List<ProductData>?>>
}