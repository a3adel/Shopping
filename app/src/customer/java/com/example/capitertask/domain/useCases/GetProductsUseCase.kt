package com.example.capitertask.domain.useCases

import com.example.capitertask.domain.dataSources.ProductRepository
import com.example.capitertask.domain.models.ProductModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val productRepository: ProductRepository) {
    fun getProducts(page: Int): Observable<ArrayList<ProductModel>> {
        return productRepository.getProducts(page).filter {
            it.filter {
                it.name.isNotEmpty() && it.price > 0
            }
            true
        }
    }
}