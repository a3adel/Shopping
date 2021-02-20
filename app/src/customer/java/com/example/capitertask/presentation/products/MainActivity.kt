package com.example.capitertask.presentation.products

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.capitertask.databinding.ActivityMainBinding
import com.example.capitertask.domain.models.ProductModel
import com.example.capitertask.presentation.base.BaseActivity
import com.example.capitertask.presentation.utils.SingleEvent
import com.example.capitertask.presentation.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: ProductsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getProducts()
        observe(viewModel.showProgressBarLiveData, ::handleProgressbarState)
        observe(viewModel.productsLiveData, ::handleProducts)
    }

    private fun handleProducts(products: List<ProductModel>) {
        showToast(products.get(0).name)
    }

    private fun handleProgressbarState(state: SingleEvent<Boolean>) {
        if (state.peekContent())
            binding.loadingPb.visibility = View.VISIBLE
        else
            binding.loadingPb.visibility = View.GONE


    }
}