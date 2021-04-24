package com.example.zarbondistributionclient.data.models.buymodel

data class BuyProductResponse(
    val client: Int,
    val deadline: Any,
    val id: Int,
    val price: String,
    val product: Int,
    val quantity: String,
    val status: String
    )