package com.example.capitertask.data.repos

import com.example.capitertask.data.api.CartAPIInterface
import com.example.capitertask.data.mappers.ProductsModelToCartProducts
import com.example.capitertask.data.models.CartResponse
import com.example.capitertask.domain.dataSources.CartRepo
import com.example.capitertask.domain.models.ProductModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CartRepoImpl @Inject constructor(private val cartRemoteClient: CartRemoteClient) : CartRepo {

    override fun createCart(
        orderName: String,
        products: List<ProductModel>
    ): Single<List<CartResponse>> = cartRemoteClient.createCart(orderName, products)
}

class CartRemoteClient @Inject constructor(
    private val cartAPIInterface: CartAPIInterface,
    private val productsToCartsMapper: ProductsModelToCartProducts
) {
    fun createCart(orderName: String, products: List<ProductModel>): Single<List<CartResponse>> {
        productsToCartsMapper.orderName = orderName

        return cartAPIInterface.createOrder(productsToCartsMapper.mapFrom(products))
    }
}