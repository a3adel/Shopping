package com.example.capitertask.presentation.utils

import com.example.capitertask.domain.models.ProductModel

interface OnRemoveItemClickListener {
    fun onRemoveClicked(productModel: ProductModel)
}