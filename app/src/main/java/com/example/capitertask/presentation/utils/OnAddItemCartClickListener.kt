package com.example.capitertask.presentation.utils

import com.example.capitertask.domain.models.ProductModel

interface OnAddItemCartClickListener {
    fun onClick(productModel: ProductModel)
}