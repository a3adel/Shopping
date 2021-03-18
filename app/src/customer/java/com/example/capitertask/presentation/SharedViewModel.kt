package com.example.capitertask.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capitertask.domain.models.ProductModel
import com.example.capitertask.presentation.base.BaseViewModel
import com.example.capitertask.presentation.utils.SingleEvent
import com.example.capitertask.presentation.utils.updateItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : BaseViewModel() {

    private val productsList = ArrayList<ProductModel>()
    private val product = MutableLiveData<ProductModel>()
    val productLiveData: LiveData<ProductModel> get() = product

    fun setSharedList(productsList: List<ProductModel>) {
        this.productsList.clear()
        this.productsList.addAll(productsList)
    }

    fun incrementItemCount(product: ProductModel) {
        product.apply { amount += 1 }
        productsList.updateItem(product)
        this.product.postValue((product))
    }

    fun setProductCountToZero(product: ProductModel) {
        product.apply { amount = 0 }
        productsList.updateItem(product)
        this.product.value=product
    }

    fun getSharedList(): List<ProductModel> = productsList
    fun clearCart() {
        val cart = productsList.filter { productModel ->
            productModel.amount > 0
        }
        Log.d("SIZEEE", "${cart.size}")
        for (product in cart)
            setProductCountToZero(product)
    }


}
