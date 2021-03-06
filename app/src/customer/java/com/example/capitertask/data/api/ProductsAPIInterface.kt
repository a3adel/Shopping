package com.example.capitertask.data.api

import com.example.capitertask.data.api.models.ProductResponse
import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.data.models.CartResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductsAPIInterface {
    @GET("products")
    fun getProducts(@Query("page") page: Int = 1): Observable<ArrayList<ProductResponse>>


}