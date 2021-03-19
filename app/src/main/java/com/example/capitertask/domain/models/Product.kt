package com.example.capitertask.domain.models

data class Product(
    val id: String,
    val name: String? = null,
    val price: Float = 0f,
    var quantity: Int = 0,
    val imageUrl: String? = null
)