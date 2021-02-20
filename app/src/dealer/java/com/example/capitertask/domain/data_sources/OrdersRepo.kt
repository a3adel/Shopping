package com.example.capitertask.domain.data_sources

import com.example.capitertask.domain.models.OrderModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface OrdersRepo {
    fun getOrders(): Single<List<OrderModel>>
}