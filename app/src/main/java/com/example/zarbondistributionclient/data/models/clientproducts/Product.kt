package com.example.zarbondistributionclient.data.models.clientproducts
data class Product(
    val category: Int,
    val id: Int,
    val image: String,
    val name: String,
    val product_type: String,
    val provider: Int,
    val unit: String
)