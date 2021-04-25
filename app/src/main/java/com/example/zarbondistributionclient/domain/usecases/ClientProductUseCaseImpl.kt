package com.example.zarbondistributionclient.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.zarbondistributionclient.data.models.clientproducts.ClientProducts
import com.example.zarbondistributionclient.domain.repositories.ClientProductsRepository
import com.example.zarbondistributionclient.domain.repositories.ClientProductsRepositoryImpl
import kotlinx.coroutines.flow.collect

class ClientProductUseCaseImpl : ClientProductsUseCase {
    private val repository: ClientProductsRepository = ClientProductsRepositoryImpl()
    override val errorClientProductsLiveData = MutableLiveData<Unit>()
    override fun getClientProducts(clientId: String): LiveData<List<ClientProducts>> = liveData {
        repository.getClientProducts(clientId).collect {
            if (it.isSuccess) {
                emit(it.getOrNull()!!)
            } else {
                errorClientProductsLiveData.postValue(Unit)
            }
        }
    }
}