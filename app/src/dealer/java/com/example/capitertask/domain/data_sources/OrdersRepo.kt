package com.example.capitertask.domain.data_sources

import com.example.capitertask.domain.models.OrderModel
import io.reactivex.rxjava3.core.Observable

interface OrdersRepo {
    fun getOrders(): Observable<List<OrderModel>>
}