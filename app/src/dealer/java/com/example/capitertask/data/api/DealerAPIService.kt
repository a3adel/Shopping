package com.example.capitertask.data.api

import com.example.capitertask.data.models.OrderData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface DealerAPIService {

    @GET("orders")
    fun getOrders(): Observable<List<OrderData>>
}