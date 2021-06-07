package com.example.capitertask.domain.dataSources

import com.example.capitertask.data.models.CartResponse
import com.example.capitertask.domain.models.Product
import io.reactivex.rxjava3.core.Single

interface CartRepo {
    fun createCart(
        orderName: String,
        products: List<Product>
    ): Single<List<CartResponse>>
}