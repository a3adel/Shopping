package com.example.capitertask.domain.models

data class ProductModel(
    val id: String,
    val name: String,
    val price: Float,
    val imageUrl: String,
    var amount: Int = 0

) {
    override fun equals(other: Any?): Boolean {
        if (other is ProductModel) {
            return (other).id == id
        }
        return false
    }
}