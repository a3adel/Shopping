package com.example.capitertask.domain.useCases

import com.example.capitertask.domain.models.ProductModel
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor() {
    fun getCartItems(products: List<ProductModel>): List<ProductModel> {
        return products.filter { productModel -> productModel.amount > 0 }
    }
}