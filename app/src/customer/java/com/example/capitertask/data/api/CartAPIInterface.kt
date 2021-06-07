package com.example.capitertask.data.api

import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.data.models.CartResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface CartAPIInterface {
    @POST("orders")
    fun createOrder(@Body orders: List<CartProduct>): Single<List<CartResponse>>
}