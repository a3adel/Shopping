package com.example.capitertask.data.repos

import com.example.capitertask.data.api.DealerAPIService
import com.example.capitertask.data.models.OrderMapper
import com.example.capitertask.domain.data_sources.OrdersRepo
import com.example.capitertask.domain.models.OrderModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class OrdersRepoImpl @Inject constructor(private val _remoteClient: RemoteOrderClient) :
    OrdersRepo {
    override fun getOrders(): Single<List<OrderModel>> {
        return _remoteClient.getOrders()
    }
}

class RemoteOrderClient @Inject constructor(
    private val _apiService: DealerAPIService,
    private val _orderMapper: OrderMapper
) {
    fun getOrders(): Single<List<OrderModel>> {
        return _apiService.getOrders().map {
            _orderMapper.mapFrom(it)
        }
    }
}