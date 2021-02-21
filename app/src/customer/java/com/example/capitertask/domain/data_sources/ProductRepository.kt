package com.example.capitertask.domain.data_sources

import androidx.lifecycle.LiveData
import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.data.models.CartResponse
import com.example.capitertask.domain.models.ProductModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ProductRepository {
    fun getProducts(page: Int = 1): Observable<List<ProductModel>>

    fun insertProduct(cartProduct: CartProduct): Single<Integer>

    fun updateProduct(cartProduct: CartProduct):Single<Integer>

    fun deleteProduct(cartProduct: CartProduct):Single<Integer>

    fun getAllProducts():LiveData<List<CartProduct>>

    fun createCart(products:List<CartProduct>):Single<List<CartResponse>>
}