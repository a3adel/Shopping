package com.example.capitertask.data.mapper

import com.example.capitertask.data.api.models.ProductResponse
import com.example.capitertask.data.mappers.ProductsModelToCartProducts
import com.example.capitertask.data.mappers.ProductsResponseToProductsMapper
import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.domain.models.ProductModel
import org.junit.Assert
import org.junit.Test

class ProductResponsesToProductModelTest {
    @Test
    fun testMapping_success() {
        val productsResponse = ArrayList<ProductResponse>()
        productsResponse.add(productResponse1)
        productsResponse.add(productResponse2)
        val testedProducts = ArrayList<ProductModel>()
        testedProducts.add(productModel)
        testedProducts.add(productModel2)
        val productsResponseToProductModel = ProductsResponseToProductsMapper()
        val products = productsResponseToProductModel.mapFrom(productsResponse)
        Assert.assertEquals(products, testedProducts)
    }
}