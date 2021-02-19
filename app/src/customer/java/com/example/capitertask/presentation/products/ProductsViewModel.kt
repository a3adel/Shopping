package com.example.capitertask.presentation.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capitertask.domain.models.ProductModel
import com.example.capitertask.domain.use_cases.GetProductsUseCase
import com.example.capitertask.presentation.base.BaseViewModel
import com.example.capitertask.presentation.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val _productsUseCase: GetProductsUseCase,
    @Named("observed") private val _observedScheduler: Scheduler,
    @Named("observer") private val _observerScheduler: Scheduler
) : BaseViewModel() {
    val productsLiveData: LiveData<List<ProductModel>>
        get() = _productsMutableLiveData

    private val _productsMutableLiveData = MutableLiveData<List<ProductModel>>()
    fun getProducts() {
        _productsUseCase.getProducts(1)
            .observeOn(_observerScheduler)
            .subscribeOn(_observedScheduler)
            .subscribe(object : Observer<List<ProductModel>> {
                override fun onSubscribe(d: Disposable?) {
                    showProgressBarMutableLiveData.value = SingleEvent<Boolean>(true)
                    d?.let { addDisposable(d) }
                }

                override fun onNext(t: List<ProductModel>?) {
                    _productsMutableLiveData.value = t
                }

                override fun onError(e: Throwable?) {
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
}