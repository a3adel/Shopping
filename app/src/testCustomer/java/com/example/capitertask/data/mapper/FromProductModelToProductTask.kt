package com.example.capitertask.data.mapper

import com.example.capitertask.data.mappers.ProductsModelToCartProducts
import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.domain.models.ProductModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException


class FromProductModelToProductTask {
    @Rule
    @JvmField
    var exception = ExpectedException.none()

    @Test
    fun testMapping_success() {
        val products = ArrayList<ProductModel>()
        products.add(productModel)
        products.add(productModel2)
        val testedCartProduct = ArrayList<CartProduct>()
        testedCartProduct.add(productCart)
        testedCartProduct.add(productCart2)
        val productsModelToCartProducts = ProductsModelToCartProducts()
        productsModelToCartProducts.orderName = orderName
        val cartProducts = productsModelToCartProducts.mapFrom(products)
        Assert.assertEquals(cartProducts, testedCartProduct)
    }

    @Test
    fun testMapping_noName_throwException() {
        exception.expect(IllegalArgumentException::class.java)

        val products = ArrayList<ProductModel>()
        products.add(productModel)
        products.add(productModel2)
        val testedCartProduct = ArrayList<CartProduct>()
        testedCartProduct.add(productCart)
        testedCartProduct.add(productCart2)
        val productsModelToCartProducts = ProductsModelToCartProducts()
        val cartProducts = productsModelToCartProducts.mapFrom(products)
    }
}