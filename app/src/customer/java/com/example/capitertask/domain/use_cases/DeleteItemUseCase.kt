package com.example.capitertask.domain.use_cases

import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.domain.data_sources.ProductRepository
import javax.inject.Inject

class DeleteItemUseCase @Inject constructor(private val _productsRepo: ProductRepository) {
    fun deleteItem(cartProduct: CartProduct) {
        _productsRepo.deleteProduct(cartProduct)
    }
}
