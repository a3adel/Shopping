package com.example.capitertask.domain.useCases

import com.example.capitertask.domain.models.Product
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor() {
    fun getCartItems(products: List<Product>): List<Product> {
        return products.filter { productModel -> productModel.quantity > 0 }
    }
}