package com.example.capitertask.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cart_products")
data class CartProduct(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("product-id")
    val id: String,
    @SerializedName("product-name")
    @ColumnInfo(name = "name")
    val name: String?=null,
    @ColumnInfo(name = "price")
    @SerializedName("product-price")
    val price: Float?=null,
    @ColumnInfo(name = "image_url")
    @SerializedName("product-image-url")
    val imageUrl: String?=null,
    @ColumnInfo(name = "quantity")
    @SerializedName("product-quantity")
    val quantity: Int?=null,
    @ColumnInfo(name = "order_name")
    @SerializedName("order-name")
    val orderName: String?=null
) {
    override fun equals(other: Any?): Boolean {
        if (other is CartProduct) {
            return (other).id == id
        }
        return false
    }
}