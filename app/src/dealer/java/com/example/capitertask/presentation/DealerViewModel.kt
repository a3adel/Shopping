package com.example.capitertask.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capitertask.domain.models.NamedOrder
import com.example.capitertask.domain.use_cases.GetOrdersByNameUseCase
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
    private val _getOrdersByNameUseCase: GetOrdersByNameUseCase,
    @Named("observer") private val _observer: Scheduler,
    @Named("observed") private val _observed: Scheduler
) :
    BaseViewModel() {
    private val _namedOrdersMutableLiveData = MutableLiveData<List<NamedOrder>>()
    val namedOrdersLiveData: LiveData<List<NamedOrder>> get() = _namedOrdersMutableLiveData
    fun getOrders() {
        _getOrdersByNameUseCase.getOrders()
            .subscribeOn(_observed)
            .observeOn(_observer)
            .subscribe(object : SingleObserver<List<NamedOrder>> {
                override fun onSubscribe(d: Disposable?) {
                    showProgressBarMutableLiveData.value = SingleEvent(true)

                    d?.let { addDisposable(d) }
                }


                override fun onError(e: Throwable?) {
                    showProgressBarMutableLiveData.value = SingleEvent(false)

                    e?.let { toastMutableLiveData.value = SingleEvent(e.localizedMessage) }
                }


                override fun onSuccess(t: List<NamedOrder>?) {
                    showProgressBarMutableLiveData.value = SingleEvent(false)
                    _namedOrdersMutableLiveData.value = t

                }
            })
    }
}