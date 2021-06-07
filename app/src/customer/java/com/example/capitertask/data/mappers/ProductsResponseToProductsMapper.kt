package com.example.capitertask.data.mappers

import com.example.capitertask.data.api.models.ProductResponse
import com.example.capitertask.domain.models.Product
import com.example.capitertask.domain.utils.Mapper
import javax.inject.Inject

class ProductsResponseToProductsMapper @Inject constructor() :
    Mapper<List<ProductResponse>, ArrayList<Product>>() {
    override fun mapFrom(from: List<ProductResponse>): ArrayList<Product> {
        val productsList = ArrayList<Product>()
        for (response in from) {
            productsList.add(mapFromResponseToModel(response))
        }
        return productsList
    }

    private fun mapFromResponseToModel(productResponse: ProductResponse): Product {
        return Product(
            id = productResponse.id,
            imageUrl = productResponse.imageUrl,
            name = productResponse.name,
            price = productResponse.price,
        )
    }
}