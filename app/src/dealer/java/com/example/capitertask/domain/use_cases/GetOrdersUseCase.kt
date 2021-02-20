package com.example.capitertask.domain.use_cases

import com.example.capitertask.domain.data_sources.OrdersRepo
import com.example.capitertask.domain.models.OrderModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(private val repo: OrdersRepo) {
    fun getOrders(): Observable<List<OrderModel>> {
        return repo.getOrders().map {
            it.filter {
                it.productName != null
            }
        }
    }
}