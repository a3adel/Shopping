package com.example.capitertask.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.domain.data_sources.ProductRepository
import javax.inject.Inject

class GetCartOrdersUseCase @Inject constructor(private val _productsRepository: ProductRepository) {
    fun getCartItems(): LiveData<List<CartProduct>> {
       return _productsRepository.getAllProducts()
    }
}