package com.example.zarbondistributionclient.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zarbondistributionclient.data.models.clientproducts.ClientProducts
import com.example.zarbondistributionclient.domain.usecases.ClientProductUseCaseImpl
import com.example.zarbondistributionclient.domain.usecases.ClientProductsUseCase
import com.example.zarbondistributionclient.utils.isConnected

class ClientProductsViewModel : ViewModel() {

    private val productsUseCase: ClientProductsUseCase = ClientProductUseCaseImpl()
    val errorProductsLiveData: LiveData<Unit> = productsUseCase.errorClientProductsLiveData
    val progressProductsLiveData = MutableLiveData<Boolean>()
    val connectionErrorProductsLiveData = MutableLiveData<Unit>()
    val successProductsLiveData = MediatorLiveData<List<ClientProducts>>()


    fun getClientProducts(clientId: Int) {
        if (isConnected()) {
            progressProductsLiveData.value = true
            val liveData = productsUseCase.getClientProducts(clientId.toString())
            successProductsLiveData.addSource(liveData) {
                progressProductsLiveData.value = false
                successProductsLiveData.value = it
                successProductsLiveData.removeSource(liveData)
            }
        } else {
            // progressLiveData.value = false
            connectionErrorProductsLiveData.value = Unit
        }
    }
}

