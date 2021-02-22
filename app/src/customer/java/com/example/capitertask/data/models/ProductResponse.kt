package com.example.capitertask.data.api.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ProductResponse(

    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Float,
    @SerializedName("image-url") val imageUrl: String,

    @SerializedName("page") val page: Int
)