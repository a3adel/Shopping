package com.example.capitertask.data.api

import com.example.capitertask.data.api.models.ProductResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import retrofit2.http.GET
import retrofit2.http.Query

interface CustomerAPIService {
    @GET("products")
    fun getProducts(@Query("page") page: Int = 1):Observable<List<ProductResponse>>
}