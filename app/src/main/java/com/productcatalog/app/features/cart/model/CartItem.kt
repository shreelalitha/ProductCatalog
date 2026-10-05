package com.productcatalog.app.features.cart.model

data class CartItem(
    val productId: Int,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val quantity: Int
)
