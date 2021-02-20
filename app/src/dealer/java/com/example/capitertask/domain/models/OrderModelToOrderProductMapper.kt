package com.example.capitertask.domain.models

import com.example.capitertask.domain.utils.Mapper
import javax.inject.Inject

class OrderModelToOrderProductMapper @Inject constructor() : Mapper<OrderModel, OrderProduct>() {
    override fun mapFrom(from: OrderModel): OrderProduct {
        return OrderProduct(
            id = from.productId,
            name = from.productName,
            price = from.productPrice,
            imageUrl = from.productImageUrl,
            quantity = from.productQuantity,
            orderId = from.id,
        )
    }
}