package com.example.capitertask.presentation.utils


interface OnAddItemCartClickListener<T> {
    fun onClick(productModel: T)
}