package com.example.capitertask.domain.dataSources

import androidx.lifecycle.LiveData
import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.data.models.CartResponse
import com.example.capitertask.domain.models.ProductModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ProductRepository {
    fun getProducts(page: Int = 1): Observable<ArrayList<ProductModel>>

    fun insertProduct(cartProduct: CartProduct): Long

    fun updateProduct(cartProduct: CartProduct): Int

    fun deleteProduct(cartProduct: CartProduct): Int

    fun getAllProducts():LiveData<List<CartProduct>>

    fun createCart(orderName:String,products:List<ProductModel>):Single<List<CartResponse>>
}