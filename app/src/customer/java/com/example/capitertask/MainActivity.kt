package com.example.capitertask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capitertask.databinding.ActivityMainBinding
import com.example.capitertask.presentation.utils.BASE

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tv.text = BASE
    }
}