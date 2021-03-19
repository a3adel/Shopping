package com.example.capitertask.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capitertask.data.models.CartResponse
import com.example.capitertask.di.AppModule
import com.example.capitertask.domain.models.Product
import com.example.capitertask.domain.useCases.CreateCartUseCase
import com.example.capitertask.domain.useCases.GetCartItemsUseCase
import com.example.capitertask.presentation.base.BaseViewModel
import com.example.capitertask.presentation.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class CartViewModel @Inject constructor(
    private val createCartUseCase: CreateCartUseCase,
    private val getCartItemsUseCase: GetCartItemsUseCase,
    @Named(AppModule.IO) private val observed: Scheduler,
    @Named(AppModule.MAIN) private val observer: Scheduler,
    private val validator: CartValidator
) : BaseViewModel() {
    private val cartListMutableLiveData = MutableLiveData<SingleEvent<List<Product>>>()
    val cartListLiveData: LiveData<SingleEvent<List<Product>>> get() = cartListMutableLiveData
    private val createCartMutableLiveData = MutableLiveData<SingleEvent<Boolean>>()
    val createCartLiveData: LiveData<SingleEvent<Boolean>> get() = createCartMutableLiveData
    fun submitOrder(orderName: String, cartProducts: List<Product>) {
        if (validator.validateOrderName(orderName) == CartValidator.ValidationResult.TRUE)
            createCartUseCase.createCart(
                orderName,
                cartProducts
            ).observeOn(observer)
                .subscribeOn(observed)
                .subscribe(object : SingleObserver<List<CartResponse>> {
                    override fun onSubscribe(d: Disposable?) {
                        d?.let { addDisposable(d) }
                        showProgressBarMutableLiveData.value = SingleEvent(true)
                    }

                    override fun onSuccess(t: List<CartResponse>?) {
                        showProgressBarMutableLiveData.value = SingleEvent(false)
                        createCartMutableLiveData.value = SingleEvent((true))
                    }

                    override fun onError(e: Throwable?) {
                        showProgressBarMutableLiveData.value = SingleEvent(false)
                        createCartMutableLiveData.value = SingleEvent((false))

                        e?.message?.let { toastMutableLiveData.value = SingleEvent(it) }
                    }

                })
        else
            validator.errorMessages.get(CartValidator.ValidationResult.NAME_ERROR)?.let { message ->
                toastMutableLiveData.postValue(SingleEvent(message))
            }
    }

    fun getCartItems(products: List<Product>) {
        cartListMutableLiveData.postValue(SingleEvent(getCartItemsUseCase.getCartItems(products)))
    }

    fun getCart(): List<Product> {
        val cartList = ArrayList<Product>()
        cartListLiveData.value?.peekContent()?.let {
            cartList.addAll(it)
        }
        return cartList
    }

}