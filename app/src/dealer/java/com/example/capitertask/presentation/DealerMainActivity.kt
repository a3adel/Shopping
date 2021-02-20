package com.example.capitertask.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.capitertask.R
import com.example.capitertask.presentation.base.BaseActivity
import com.example.capitertask.presentation.utils.SingleEvent
import com.example.capitertask.presentation.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealerMainActivity : BaseActivity() {
    private val _viewModel:DealerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dealer_main)
        _viewModel.getOrders()
        observe(_viewModel.toastLiveData,::handleToast)
    }

    private fun handleToast(singleEvent: SingleEvent<String>) {
        showToast(singleEvent.peekContent())
    }
}