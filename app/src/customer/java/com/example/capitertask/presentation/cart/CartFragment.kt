package com.example.capitertask.presentation.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capitertask.databinding.FragmentCartBinding
import com.example.capitertask.domain.models.ProductModel
import com.example.capitertask.presentation.products.ProductsViewModel
import com.example.capitertask.presentation.utils.OnRemoveItemClickListener
import com.example.capitertask.presentation.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val _adapter = CartProductsAdapter()

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    private val _viewModel: ProductsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModels()
        _viewModel.getCart()
    }

    private fun initViews() {
        binding.cartOrdersRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.cartOrdersRecyclerView.adapter = _adapter
        _adapter.onRemoveItemClickListener = object : OnRemoveItemClickListener {
            override fun onRemoveClicked(productModel: ProductModel) {
                _adapter.removeItem(productModel)
            }
        }
    }

    private fun observeViewModels() {
        observe(_viewModel.cartListLiveData, ::handleCart)
    }

    private fun handleCart(arrayList: ArrayList<ProductModel>) {
        _adapter.updateProducts(arrayList)
    }
}