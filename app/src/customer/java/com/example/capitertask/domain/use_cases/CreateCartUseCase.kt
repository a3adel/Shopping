package com.example.capitertask.domain.use_cases

import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.domain.data_sources.ProductRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CreateCartUseCase @Inject constructor(private val _productsRepository: ProductRepository) {
    fun createCart(products:List<CartProduct>):Single<List<CartProduct>>{
        return _productsRepository.createCart(products)
    }
}