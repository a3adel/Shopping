package com.example.capitertask.data.mappers

import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.domain.models.Product
import com.example.capitertask.domain.utils.Mapper
import javax.inject.Inject

class ProductsModelToCartProducts @Inject constructor() :
    Mapper<List<Product>, List<CartProduct>>() {
    var orderName = ""
    override fun mapFrom(from: List<Product>): List<CartProduct> {
        if (orderName.isEmpty())
            throw IllegalArgumentException(" please set the orderName before using the map function")

        val cartProducts = ArrayList<CartProduct>()
        for (product in from) {
            cartProducts.add(mapProductModelToCartModel(product))
        }
        return cartProducts
    }

    fun mapProductModelToCartModel(productModel: Product): CartProduct {
        return CartProduct(
            id = productModel.id,
            price = productModel.price,
            name = productModel.name,
            quantity = productModel.quantity,
            imageUrl = productModel.imageUrl,
            orderName = orderName
        )
    }

}