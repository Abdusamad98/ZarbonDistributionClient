package com.example.zarbondistributionclient.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.zarbondistributionclient.data.models.buymodel.BuyProductData
import com.example.zarbondistributionclient.data.models.buymodel.BuyProductResponse
import com.example.zarbondistributionclient.domain.repositories.BuyProductRepository
import com.example.zarbondistributionclient.domain.repositories.BuyProductRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class BuyProductUseCaseImpl : BuyProductUseCase {
    private val repository: BuyProductRepository = BuyProductRepositoryImpl()
    override val errorNotResponseLiveData = MutableLiveData<String>()
    override val errorResponseLiveData = MutableLiveData<String>()

    override fun buyProduct(buyProductData: BuyProductData): LiveData<BuyProductResponse> =
        liveData(Dispatchers.IO) {
            repository.buyProduct(buyProductData).collect{
                if (it.isSuccess) {
                    it.getOrNull()?.let { pair ->
                        if (pair.first == 201) pair.second?.let { it1 -> emit(it1) }
                        if (pair.first == 400) errorResponseLiveData.postValue("Omborda maxsulot yetarli emas!")
                    }
                } else {
                    errorNotResponseLiveData.postValue("Error")
                }
            }
        }

}