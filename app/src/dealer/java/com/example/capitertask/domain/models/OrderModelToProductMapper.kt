package com.example.capitertask.domain.models

import com.example.capitertask.domain.utils.Mapper
import javax.inject.Inject

class OrderModelToProductMapper @Inject constructor() : Mapper<OrderModel, Product>() {
    override fun mapFrom(from: OrderModel): Product {
        return Product(
            id = from.productId,
            name = from.productName,
            price = from.productPrice,
            imageUrl = from.productImageUrl,
            quantity = from.productQuantity
        )
    }
}