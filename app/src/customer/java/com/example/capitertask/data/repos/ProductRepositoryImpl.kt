package com.example.capitertask.data.repos

import androidx.lifecycle.LiveData
import com.example.capitertask.data.api.ProductsAPIInterface
import com.example.capitertask.data.mappers.ProductsResponseToProductsMapper
import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.data.persistence.CartDao
import com.example.capitertask.domain.dataSources.ProductRepository
import com.example.capitertask.domain.models.Product
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productRemoteClient: ProductRemoteClient,
    private val productsLocalClient: ProductLocalClient
) :
    ProductRepository {
    override fun getProducts(page: Int): Observable<ArrayList<Product>> =
        productRemoteClient.getProducts(page)


    override fun insertProduct(cartProduct: CartProduct): Long =
        productsLocalClient.insertProduct(cartProduct)


    override fun updateProduct(cartProduct: CartProduct): Int =
        productsLocalClient.updateProduct(cartProduct)


    override fun deleteProduct(cartProduct: CartProduct): Int =
        productsLocalClient.deleteProduct(cartProduct)


    override fun getAllProducts(): LiveData<List<CartProduct>> =
        productsLocalClient.getCartProducts()




}

class ProductRemoteClient @Inject constructor(
    private val apiService: ProductsAPIInterface,
    private val mapper: ProductsResponseToProductsMapper) {
    fun getProducts(page: Int): Observable<ArrayList<Product>> {
        return apiService.getProducts(page).map {
            mapper.mapFrom(it)
        }
    }


}

class ProductLocalClient @Inject constructor(private val db: CartDao) {
    fun insertProduct(cartProduct: CartProduct): Long = db.insertProduct(cartProduct)


    fun updateProduct(cartProduct: CartProduct): Int = db.updateProduct(cartProduct)


    fun deleteProduct(cartProduct: CartProduct): Int = db.deleteProduct(cartProduct)


    fun getCartProducts(): LiveData<List<CartProduct>> = db.getCartProducts()

}