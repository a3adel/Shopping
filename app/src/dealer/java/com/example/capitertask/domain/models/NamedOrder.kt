package com.example.capitertask.domain.models

data class NamedOrder(

    val name: String,
    val products: List<OrderProduct>?=null
)