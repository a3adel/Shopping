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
    val name: String,
    @ColumnInfo(name = "price")
    @SerializedName("product-price")
    val price: Float,
    @ColumnInfo(name = "image_url")
    @SerializedName("product-image-url")
    val imageUrl: String,
    @ColumnInfo(name = "quantity")
    @SerializedName("product-quantity")
    val quantity: Int,
    @ColumnInfo(name = "order_name")
    @SerializedName("order-name")
    val orderName: String
) {
    override fun equals(other: Any?): Boolean {
        if (other is CartProduct) {
            return (other).id == id
        }
        return false
    }
}