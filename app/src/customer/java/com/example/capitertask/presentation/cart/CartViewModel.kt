package com.example.capitertask.presentation.cart

import com.example.capitertask.data.models.CartProduct
import com.example.capitertask.domain.use_cases.CartInteractor
import com.example.capitertask.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class CartViewModel @Inject constructor(private val _interactor: CartInteractor) : BaseViewModel() {
    fun loadCartOrders() {

    }
    fun confirmOrder() {

    }
    fun deleteItemFromCart(cartProduct: CartProduct) {
        _interactor.deleteItemUseCase.deleteItem(cartProduct)
    }
}