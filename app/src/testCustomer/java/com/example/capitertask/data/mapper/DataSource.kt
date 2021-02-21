package com.example.capitertask.data.mapper

import com.example.capitertask.data.api.models.ProductResponse
import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.domain.models.ProductModel

val productModel = ProductModel(
    name = "product1", id = "123", price = 10.0f, imageUrl = "google.com", amount = 0
)
val productModel2 = ProductModel(
    name = "product2", id = "124", price = 10.0f, imageUrl = "google.com", amount = 0
)
val orderName = "nice"
val productCart = CartProduct(
    name = "product1", id = "123", price = 10.0f, imageUrl = "google.com", quantity = 0, orderName = "nice"
)

val productCart2 = CartProduct(
    name = "product2", id = "124", price = 10.0f, imageUrl = "google.com", quantity = 0, orderName = "nice"
)

val productResponse1 = ProductResponse(id = "123",name = "product1",price = 10.0f,imageUrl = "google.com",page = 1)
val productResponse2 = ProductResponse(id = "124",name = "product1",price = 10.0f,imageUrl = "google.com",page = 2)