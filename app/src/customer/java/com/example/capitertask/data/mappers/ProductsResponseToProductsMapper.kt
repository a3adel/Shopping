package com.example.capitertask.data.mappers

import com.example.capitertask.data.api.models.ProductResponse
import com.example.capitertask.domain.models.ProductModel
import com.example.capitertask.domain.utils.Mapper
import javax.inject.Inject

class ProductsResponseToProductsMapper @Inject constructor() : Mapper<List<ProductResponse>, ArrayList<ProductModel>>() {
    override fun mapFrom(from: List<ProductResponse>): ArrayList<ProductModel> {
        val productsList = ArrayList<ProductModel>()
        for (response in from) {
            productsList.add(mapFromResponseToModel(response))
        }
        return productsList
    }

    private fun mapFromResponseToModel(productResponse: ProductResponse): ProductModel {
        return ProductModel(
            id = productResponse.id,
            imageUrl = productResponse.imageUrl,
            name = productResponse.name,
            price = productResponse.price,
        )
    }
}