package com.example.capitertask.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capitertask.presentation.utils.SingleEvent
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel : ViewModel() {
    protected val showProgressBarMutableLiveData = MutableLiveData<SingleEvent<Boolean>>()
    val showProgressBarLiveData: LiveData<SingleEvent<Boolean>> get() = showProgressBarMutableLiveData

    val toastLiveData: LiveData<SingleEvent<String>>
        get() = toastMutableLiveData

    protected val toastMutableLiveData = MutableLiveData<SingleEvent<String>>()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposables()
    }
}