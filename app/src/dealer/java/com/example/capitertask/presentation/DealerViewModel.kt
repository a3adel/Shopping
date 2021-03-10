package com.example.capitertask.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capitertask.domain.models.NamedOrder
import com.example.capitertask.domain.useCases.GetOrdersByNameUseCase
import com.example.capitertask.presentation.base.BaseViewModel
import com.example.capitertask.presentation.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DealerViewModel @Inject constructor(
    private val getOrdersByNameUseCase: GetOrdersByNameUseCase,
    @Named("observer") private val observer: Scheduler,
    @Named("observed") private val observed: Scheduler
) :
    BaseViewModel() {
    private val namedOrdersMutableLiveData = MutableLiveData<List<NamedOrder>>()
    val namedOrdersLiveData: LiveData<List<NamedOrder>> get() = namedOrdersMutableLiveData
    fun getOrders() {
        getOrdersByNameUseCase.getOrders()
            .subscribeOn(observed)
            .observeOn(observer)
            .subscribe(object : SingleObserver<List<NamedOrder>> {
                override fun onSubscribe(d: Disposable?) {
                    showProgressBarMutableLiveData.postValue( SingleEvent(true))

                    d?.let { addDisposable(d) }
                }


                override fun onError(e: Throwable?) {
                    showProgressBarMutableLiveData.postValue(SingleEvent(false))

                    e?.let { toastMutableLiveData.postValue(SingleEvent(e.localizedMessage)) }
                }


                override fun onSuccess(t: List<NamedOrder>?) {
                    showProgressBarMutableLiveData.value = SingleEvent(false)
                    namedOrdersMutableLiveData.value = t

                }
            })
    }
}