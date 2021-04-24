package com.example.zarbondistributionclient.ui.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zarbondistributionclient.data.models.productsmodel.AboutProduct
import com.example.zarbondistributionclient.domain.usecases.AboutProductUseCase
import com.example.zarbondistributionclient.domain.usecases.AboutProductUseCaseImpl
import com.example.zarbondistributionclient.utils.isConnected

class GetAboutProductViewModel : ViewModel() {

    private val useCase: AboutProductUseCase = AboutProductUseCaseImpl()
    val errorNotResponseLiveData: LiveData<String> = useCase.errorNotResponseLiveData
    val errorResponseLiveData = MediatorLiveData<String>()
    val progressAboutProductLiveData = MutableLiveData<Boolean>()
    val connectionErrorLiveData = MutableLiveData<Unit>()
    val successLiveData = MediatorLiveData<AboutProduct>()

    init {

    }


    fun getAboutProduct(id: Int) {
        if (isConnected()) {
            progressAboutProductLiveData.value = true
            val lvd = useCase.getAboutProduct(id)
            successLiveData.addSource(lvd) {
                progressAboutProductLiveData.value = false
                successLiveData.value = it
                successLiveData.removeSource(lvd)
            }
        } else {
            connectionErrorLiveData.value = Unit
        }

    }


}