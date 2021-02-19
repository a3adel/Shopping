package com.example.capitertask.data.api.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cart_products")
data class ProductResponse(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("_id") val id: String,
    @ColumnInfo(name = "name")
    @SerializedName("name") val name: String,
    @ColumnInfo(name = "price")
    @SerializedName("price") val price: Float,
    @ColumnInfo(name = "imageUrl")
    @SerializedName("image-url") val imageUrl: String,
    @Ignore
    @SerializedName("page") val page: Int
)