package com.example.zarbondistributionclient.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionclient.data.models.buymodel.BuyProductData
import com.example.zarbondistributionclient.data.models.buymodel.BuyProductResponse

interface BuyProductUseCase {
    val errorNotResponseLiveData: LiveData<String>
    val errorResponseLiveData: LiveData<String>

    fun buyProduct(productData: BuyProductData): LiveData<BuyProductResponse>
}