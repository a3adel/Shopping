package com.example.capitertask.presentation.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capitertask.databinding.FragmentProductsBinding
import com.example.capitertask.domain.models.ProductModel
import com.example.capitertask.presentation.MainActivity
import com.example.capitertask.presentation.utils.OnAddItemCartClickListener
import com.example.capitertask.presentation.utils.SingleEvent
import com.example.capitertask.presentation.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private var _binding: FragmentProductsBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: ProductsViewModel by activityViewModels()
    private val _adapter = ProductsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        viewModel.getProducts()
        observeLiveData()
    }

    override fun onResume() {
        super.onResume()

    }
    private fun initViews() {
        _adapter.setOnAddItemToCartClickListener(object : OnAddItemCartClickListener<ProductModel> {
            override fun onClick(productModel: ProductModel) {
                viewModel.addToCart(productModel)
            }
        })
        binding.productsRecyclerView.adapter = _adapter
        binding.productsRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun observeLiveData() {
        observe(viewModel.showProgressBarLiveData, ::handleProgressbarState)
        observe(viewModel.productsLiveData, ::handleProducts)
        observe(viewModel.productLiveData, ::handleAmount)
    }

    private fun handleAmount(singleEvent: SingleEvent<ProductModel>) {
        _adapter.updateItem(singleEvent.peekContent())
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