package com.example.capitertask.presentation.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capitertask.R
import com.example.capitertask.databinding.ActivityCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)

    }
}