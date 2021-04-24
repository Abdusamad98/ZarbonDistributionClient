package com.example.zarbondistributionclient.data.models.productsmodel

data class ProductData(
    val id: Int,
    val last_update: String,
    val name: String,
    val product_type: String,
    val image: String = "",
    val provider: String,
    val quantity: String,
    val unit: String,
    val price:Int,
    val price_id:Int
)

