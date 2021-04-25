package com.example.zarbondistributionclient.domain.repositories

import com.example.zarbondistributionclient.data.models.clientproducts.ClientProducts
import kotlinx.coroutines.flow.Flow

interface ClientProductsRepository {
    suspend fun getClientProducts(clientId: String): Flow<Result<List<ClientProducts>?>>
}