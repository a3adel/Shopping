package com.example.capitertask.data.repos

import androidx.lifecycle.LiveData
import com.example.capitertask.data.api.CustomerAPIService
import com.example.capitertask.data.mappers.ProductsMapper
import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.data.models.CartResponse
import com.example.capitertask.data.persistence.CartDao
import com.example.capitertask.domain.data_sources.ProductRepository
import com.example.capitertask.domain.models.ProductModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val _productRemoteClient: ProductRemoteClient,
    private val _productsLocalClient: ProductLocalClient
) :
    ProductRepository {
    override fun getProducts(page: Int): Observable<ArrayList<ProductModel>> {
        return _productRemoteClient.getProducts(page)
    }

    override fun insertProduct(cartProduct: CartProduct): Long{
        return _productsLocalClient.insertProduct(cartProduct)
    }

    override fun updateProduct(cartProduct: CartProduct): Int {
        return _productsLocalClient.updateProduct(cartProduct)
    }

    override fun deleteProduct(cartProduct: CartProduct): Int {
        return _productsLocalClient.deleteProduct(cartProduct)
    }

    override fun getAllProducts(): LiveData<List<CartProduct>> {
        return _productsLocalClient.getCartProducts()
    }

    override fun createCart(products: List<CartProduct>): Single<List<CartResponse>> {
        return _productRemoteClient.createCart(products)
    }
}

class ProductRemoteClient @Inject constructor(
    private val _apiService: CustomerAPIService,
    private val _mapper: ProductsMapper
) {
    fun getProducts(page: Int): Observable<ArrayList<ProductModel>> {
        return _apiService.getProducts(page).map {
            _mapper.mapFrom(it)
        }
    }

    fun createCart(products: List<CartProduct>): Single<List<CartResponse>> {
        return _apiService.createOrder(products)
    }
}

class ProductLocalClient @Inject constructor(private val _db: CartDao) {
    fun insertProduct(cartProduct: CartProduct): Long {
        return _db.insertProduct(cartProduct)
    }

    fun updateProduct(cartProduct: CartProduct): Int{
        return _db.updateProduct(cartProduct)
    }

    fun deleteProduct(cartProduct: CartProduct): Int{
        return _db.deleteProduct(cartProduct)
    }

    fun getCartProducts(): LiveData<List<CartProduct>> {
        return _db.getCartProducts()
    }
}