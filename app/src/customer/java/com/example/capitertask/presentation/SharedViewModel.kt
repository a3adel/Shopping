package com.example.capitertask.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capitertask.domain.models.Product
import com.example.capitertask.presentation.base.BaseViewModel
import com.example.capitertask.presentation.utils.updateItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : BaseViewModel() {

    private val productsList = ArrayList<Product>()
    private val product = MutableLiveData<Product>()
    val productLiveData: LiveData<Product> get() = product

    fun setSharedList(productsList: List<Product>) {
        this.productsList.clear()
        this.productsList.addAll(productsList)
    }

    fun incrementItemCount(product: Product) {
        product.apply { quantity += 1 }
        productsList.updateItem(product)
        this.product.postValue((product))
    }

    fun setProductCountToZero(product: Product) {
        product.apply { quantity = 0 }
        productsList.updateItem(product)
        this.product.value=product
    }

    fun getSharedList(): List<Product> = productsList
    fun clearCart() {
        val cart = productsList.filter { productModel ->
            productModel.quantity > 0
        }
        Log.d("SIZEEE", "${cart.size}")
        for (product in cart)
            setProductCountToZero(product)
    }


}
