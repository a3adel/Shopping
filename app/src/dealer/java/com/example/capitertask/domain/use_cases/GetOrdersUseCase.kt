package com.example.capitertask.domain.use_cases

import com.example.capitertask.domain.data_sources.OrdersRepo
import com.example.capitertask.domain.models.OrderModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(private val repo: OrdersRepo) {
    fun getOrders(): Single<List<OrderModel>> {
        return repo.getOrders().map {
            it.filter {
                it.productName != null
            }
        }
    }
}