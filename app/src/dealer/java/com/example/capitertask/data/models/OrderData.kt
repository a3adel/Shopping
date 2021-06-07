package com.example.capitertask.data.models

import com.google.gson.annotations.SerializedName

data class OrderData(
    @SerializedName("_id") val id: String,
    @SerializedName("product-id") val productId: String,
    @SerializedName("order-name") val name: String? = null,
    @SerializedName("product-quantity") val productQuantity: Int? = null,
    @SerializedName("product-price") val productPrice: Float? = null,
    @SerializedName("product-name") val productName: String? = null,
    @SerializedName("product-image-url") val productImageUrl: String? = null
)
