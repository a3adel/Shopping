package com.example.capitertask.presentation.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capitertask.di.AppModule
import com.example.capitertask.domain.models.Product
import com.example.capitertask.domain.useCases.GetProductsUseCase
import com.example.capitertask.presentation.base.BaseViewModel
import com.example.capitertask.presentation.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    @Named(AppModule.IO) private val ioScheduler: Scheduler,
    @Named(AppModule.MAIN) private val mainScheduler: Scheduler
) : BaseViewModel() {

    private val _productsMutableLiveData = MutableLiveData<ArrayList<Product>>()
    val productsLiveData: LiveData<ArrayList<Product>>
        get() = _productsMutableLiveData


    fun getProducts() {
        getProductsUseCase.getProducts(1)
            .observeOn(mainScheduler)
            .subscribeOn(ioScheduler)
            .subscribe(object : Observer<ArrayList<Product>> {
                override fun onSubscribe(d: Disposable?) {
                    showProgressBarMutableLiveData.value = SingleEvent<Boolean>(true)
                    d?.let { addDisposable(d) }
                }

                override fun onNext(t: ArrayList<Product>?) {
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


}