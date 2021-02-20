package com.example.capitertask.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.capitertask.R
import com.example.capitertask.presentation.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealerMainActivity : AppCompatActivity() {
    private val _viewModel:DealerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dealer_main)
        _viewModel.getOrders()
    }
}