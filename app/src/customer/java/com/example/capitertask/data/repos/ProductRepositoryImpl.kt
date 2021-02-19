package com.example.capitertask.data.repos

import com.example.capitertask.data.api.CustomerAPIService
import com.example.capitertask.data.mappers.ProductsMapper
import com.example.capitertask.domain.data_sources.ProductRepository
import com.example.capitertask.domain.models.ProductModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(private val _productRemoteClient: ProductRemoteClient) :
    ProductRepository {
    override fun getProducts(page: Int): Observable<List<ProductModel>> {
        return _productRemoteClient.getProducts(page)
    }
}

class ProductRemoteClient @Inject constructor(
    private val _apiService: CustomerAPIService,
    private val _mapper: ProductsMapper
) {
    fun getProducts(page: Int): Observable<List<ProductModel>> {
        return _apiService.getProducts(page).map {
            _mapper.mapFrom(it) }
    }
}