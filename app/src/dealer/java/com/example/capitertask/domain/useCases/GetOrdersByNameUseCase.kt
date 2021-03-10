package com.example.capitertask.domain.useCases

import com.example.capitertask.domain.data_sources.OrdersRepo
import com.example.capitertask.domain.models.NamedOrder
import com.example.capitertask.domain.models.OrderModelToOrderProductMapper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetOrdersByNameUseCase @Inject constructor(private val repo: OrdersRepo, private val mapper: OrderModelToOrderProductMapper) {
    fun getOrders(): Single<List<NamedOrder>> {
        return repo.getOrders().map { orders->
            val namedOrders = ArrayList<NamedOrder>()
            val groupByName = orders.filter {
                it.productName != null
            }.groupBy { it.name }
            for(key in groupByName.keys){
                val namedOrder = key?.let { NamedOrder(name = it,products = groupByName.get(key)?.map { mapper.mapFrom(it) }) }
                namedOrder?.let { namedOrders.add(it) }
            }
            namedOrders
        }

    }
}