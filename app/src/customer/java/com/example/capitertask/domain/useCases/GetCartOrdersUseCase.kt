package com.example.capitertask.domain.useCases

import androidx.lifecycle.LiveData
import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.domain.dataSources.ProductRepository
import javax.inject.Inject

class GetCartOrdersUseCase @Inject constructor(private val productsRepository: ProductRepository) {
    fun getCartItems(): LiveData<List<CartProduct>> = productsRepository.getAllProducts()

}