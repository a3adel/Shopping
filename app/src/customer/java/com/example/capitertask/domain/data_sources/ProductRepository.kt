package com.example.capitertask.domain.data_sources

import com.example.capitertask.domain.models.ProductModel
import io.reactivex.rxjava3.core.Observable

interface ProductRepository {
    fun getProducts(page: Int = 1): Observable<List<ProductModel>>
}