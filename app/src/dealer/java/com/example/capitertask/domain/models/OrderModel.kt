package com.example.capitertask.domain.models

data class OrderModel(
    val id: String,
    val productId: String,
    val name: String? = null,
    val productQuantity: Int? = null,
    val productName:String?=null,
    val productPrice: Float? = null,
    val productImageUrl: String? = null
) {
}