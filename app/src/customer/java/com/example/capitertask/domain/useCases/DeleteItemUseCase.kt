package com.example.capitertask.domain.useCases

import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.domain.dataSources.ProductRepository
import javax.inject.Inject

class DeleteItemUseCase @Inject constructor(private val productsRepo: ProductRepository) {
    fun deleteItem(cartProduct: CartProduct) =
        productsRepo.deleteProduct(cartProduct)

}
