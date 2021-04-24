package com.example.zarbondistributionclient.domain.repositories

import com.example.zarbondistributionclient.data.models.buymodel.BuyProductData
import com.example.zarbondistributionclient.data.models.buymodel.BuyProductResponse
import kotlinx.coroutines.flow.Flow

interface BuyProductRepository {
    suspend fun buyProduct(productData: BuyProductData)
            : Flow<Result<Pair<Int, BuyProductResponse?>>>
}