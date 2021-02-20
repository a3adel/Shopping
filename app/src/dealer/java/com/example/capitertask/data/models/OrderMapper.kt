package com.example.capitertask.data.models

import com.example.capitertask.domain.models.OrderModel
import com.example.capitertask.domain.utils.Mapper
import javax.inject.Inject

class OrderMapper @Inject constructor() : Mapper<List<OrderData>, List<OrderModel>>() {
    override fun mapFrom(from: List<OrderData>): List<OrderModel> {
        val orders = ArrayList<OrderModel>()
        for (order in from) {
            orders.add(mapOrderResponseToOrderModel(order))
        }
        return orders
    }

    private fun mapOrderResponseToOrderModel(orderData: OrderData): OrderModel {
        return OrderModel(
            id = orderData.id,
            name = orderData.name,
            productId = orderData.productId,
            productPrice = orderData.productPrice,
            productQuantity = orderData.productQuantity,
            productImageUrl = orderData.productImageUrl,
            productName = orderData.productName
        )
    }
}