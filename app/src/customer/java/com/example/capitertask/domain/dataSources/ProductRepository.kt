package com.example.capitertask.domain.dataSources

import androidx.lifecycle.LiveData
import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.data.models.CartResponse
import com.example.capitertask.domain.models.Product
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ProductRepository {
    fun getProducts(page: Int = 1): Observable<ArrayList<Product>>

    fun insertProduct(cartProduct: CartProduct): Long

    fun updateProduct(cartProduct: CartProduct): Int

    fun deleteProduct(cartProduct: CartProduct): Int

    fun getAllProducts():LiveData<List<CartProduct>>

}