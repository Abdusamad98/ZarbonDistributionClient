package com.example.zarbondistributionclient.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionclient.data.models.clientproducts.ClientProducts

interface ClientProductsUseCase {
    val errorClientProductsLiveData: LiveData<Unit>
    fun getClientProducts(clientId: String): LiveData<List<ClientProducts>>
}