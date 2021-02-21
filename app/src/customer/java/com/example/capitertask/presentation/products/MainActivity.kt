package com.example.capitertask.presentation.products

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capitertask.databinding.ActivityMainBinding
import com.example.capitertask.domain.models.ProductModel
import com.example.capitertask.presentation.OnAddItemCartClickListener
import com.example.capitertask.presentation.base.BaseActivity
import com.example.capitertask.presentation.utils.SingleEvent
import com.example.capitertask.presentation.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: ProductsViewModel by viewModels()
    private val _adapter = ProductsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        viewModel.getProducts()
        observeLiveData()

    }

    private fun initViews() {
        _adapter.setOnAddItemToCartClickListener(object :OnAddItemCartClickListener{
            override fun onClick(productModel: ProductModel) {
                showToast(productModel.name)
            }
        })
        binding.productsRecyclerView.adapter = _adapter
        binding.productsRecyclerView.layoutManager = LinearLayoutManager(this)


    }

    private fun observeLiveData() {
        observe(viewModel.showProgressBarLiveData, ::handleProgressbarState)
        observe(viewModel.productsLiveData, ::handleProducts)
    }

    private fun handleProducts(products: List<ProductModel>) {
        _adapter.setProducts(products)
    }

    private fun handleProgressbarState(state: SingleEvent<Boolean>) {
        if (state.peekContent())
            binding.loadingPb.visibility = View.VISIBLE
        else
            binding.loadingPb.visibility = View.GONE


    }
}