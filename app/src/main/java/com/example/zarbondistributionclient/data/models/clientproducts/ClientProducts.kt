package com.example.zarbondistributionclient.data.models.clientproducts
data class ClientProducts(
    val created_date: String,
    val deadline: String="",
    val debt: String,
    val discount: String,
    val id: Int,
    val order_method: String,
    val price: Price,
    val product: Product,
    val quantity: String,
    val status: String,
    val updated_date: String
)