package com.example.capitertask.domain.models

data class OrderProduct(
    val id:String,
    val name: String?=null,
    val price: Float?=null,
    val quantity: Int?=null,
    val imageUrl:String?=null,
    val orderId:String?=null
)