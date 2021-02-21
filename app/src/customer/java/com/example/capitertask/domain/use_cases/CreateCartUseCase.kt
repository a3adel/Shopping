package com.example.capitertask.domain.use_cases

import com.example.capitertask.data.models.CartResponse
import com.example.capitertask.domain.data_sources.ProductRepository
import com.example.capitertask.domain.models.ProductModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CreateCartUseCase @Inject constructor(private val _productsRepository: ProductRepository) {
    fun createCart(orderName: String, products: List<ProductModel>): Single<List<CartResponse>> {
        return _productsRepository.createCart(products)
    }
}