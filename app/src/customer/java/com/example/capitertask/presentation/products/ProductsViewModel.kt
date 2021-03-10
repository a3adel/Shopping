package com.example.capitertask.presentation.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capitertask.data.models.CartResponse
import com.example.capitertask.domain.models.ProductModel
import com.example.capitertask.domain.useCases.CartInteractor
import com.example.capitertask.domain.useCases.CreateCartUseCase
import com.example.capitertask.domain.useCases.GetProductsUseCase
import com.example.capitertask.presentation.base.BaseViewModel
import com.example.capitertask.presentation.utils.SingleEvent
import com.example.capitertask.presentation.utils.updateItem
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val createCartUseCase: CreateCartUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    @Named("observed") private val _observedScheduler: Scheduler,
    @Named("observer") private val _observerScheduler: Scheduler
) : BaseViewModel() {
    private val cartList = ArrayList<ProductModel>()

    private val _productsMutableLiveData = MutableLiveData<ArrayList<ProductModel>>()
    val productsLiveData: LiveData<ArrayList<ProductModel>>
        get() = _productsMutableLiveData

    private val _cartListMutlableLiveData = MutableLiveData<ArrayList<ProductModel>>()
    val cartListLiveData: LiveData<ArrayList<ProductModel>> get() = _cartListMutlableLiveData

    private val _productMutableLiveData = MutableLiveData<SingleEvent<ProductModel>>()
    val productLiveData: LiveData<SingleEvent<ProductModel>> get() = _productMutableLiveData

    private val _createCartLiveData = MutableLiveData<SingleEvent<Boolean>>()
    val createCartLiveData: LiveData<SingleEvent<Boolean>> get() = _createCartLiveData

    fun addToCart(productModel: ProductModel) {
        var currentAmount = productModel.amount
        if (currentAmount != null) {
            currentAmount += 1
            productModel.amount = currentAmount
            _productMutableLiveData.value = SingleEvent(productModel)
            if (_cartListMutlableLiveData.value == null) {
                _cartListMutlableLiveData.value = cartList
            }
            if (_cartListMutlableLiveData.value != null &&
                _cartListMutlableLiveData.value!!.contains(productModel)
            ) {
                val index = _cartListMutlableLiveData.value?.indexOf(productModel)
                productModel.amount = currentAmount
                _cartListMutlableLiveData.value?.set(index!!, productModel)
            } else
                _cartListMutlableLiveData.value?.add(productModel)
        }
    }

    fun removeFromCart(productModel: ProductModel) {
        setProductAmountToZero(productModel)
        _cartListMutlableLiveData.value?.remove(productModel)
    }

    private fun setProductAmountToZero(productModel: ProductModel) {
        val products = _productsMutableLiveData.value
        products?.let {
            productModel.amount = 0
            products.updateItem(productModel)
            _productsMutableLiveData.postValue(products)
        }
    }

    fun getProducts() {
        getProductsUseCase.getProducts(1)
            .observeOn(_observerScheduler)
            .subscribeOn(_observedScheduler)
            .subscribe(object : Observer<ArrayList<ProductModel>> {
                override fun onSubscribe(d: Disposable?) {
                    showProgressBarMutableLiveData.value = SingleEvent<Boolean>(true)
                    d?.let { addDisposable(d) }
                }

                override fun onNext(t: ArrayList<ProductModel>?) {
                    _productsMutableLiveData.value = t
                }

                override fun onError(e: Throwable?) {
                    showProgressBarMutableLiveData.value = SingleEvent<Boolean>(false)
                    e?.let {
                        it.message?.let {
                            toastMutableLiveData.value = SingleEvent(it)
                        }
                    }
                }

                override fun onComplete() {
                    showProgressBarMutableLiveData.value = SingleEvent<Boolean>(false)
                }
            })
    }

    fun getCart() {
        _cartListMutlableLiveData.postValue(_cartListMutlableLiveData.value)
    }

    fun submitOrder(orderName: String) {
        val orderProucts = _cartListMutlableLiveData.value

        _cartListMutlableLiveData.value?.let {
            createCartUseCase.createCart(
                orderName,
                it.toList()
            ).observeOn(_observerScheduler)
                .subscribeOn(_observedScheduler)
                .subscribe(object : SingleObserver<List<CartResponse>> {
                    override fun onSubscribe(d: Disposable?) {
                        d?.let { addDisposable(d) }
                        showProgressBarMutableLiveData.value = SingleEvent(true)
                    }

                    override fun onSuccess(t: List<CartResponse>?) {
                        showProgressBarMutableLiveData.value = SingleEvent(false)
                        _createCartLiveData.value = SingleEvent((true))
                    }

                    override fun onError(e: Throwable?) {
                        showProgressBarMutableLiveData.value = SingleEvent(false)
                        _createCartLiveData.value = SingleEvent((false))

                        e?.message?.let { toastMutableLiveData.value = SingleEvent(it) }
                    }

                })
        }
    }

    fun clearCart() {
        _cartListMutlableLiveData.value?.let {
            for (product in it)
                setProductAmountToZero(product)
        }
        _cartListMutlableLiveData.value?.clear()

    }
}