package com.example.capitertask.domain.useCases

import com.example.capitertask.data.models.CartResponse
import com.example.capitertask.domain.dataSources.CartRepo
import com.example.capitertask.domain.models.ProductModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CreateCartUseCase @Inject constructor(private val cartRepository: CartRepo) {
    fun createCart(orderName: String, products: List<ProductModel>): Single<List<CartResponse>> =
        cartRepository.createCart(orderName, products)

}