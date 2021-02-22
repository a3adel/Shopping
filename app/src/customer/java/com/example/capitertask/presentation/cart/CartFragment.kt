package com.example.capitertask.presentation.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capitertask.R
import com.example.capitertask.databinding.FragmentCartBinding
import com.example.capitertask.domain.models.ProductModel
import com.example.capitertask.presentation.MainActivity
import com.example.capitertask.presentation.products.ProductsViewModel
import com.example.capitertask.presentation.utils.OnRemoveItemClickListener
import com.example.capitertask.presentation.utils.SingleEvent
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
        _adapter.onRemoveItemClickListener = object : OnRemoveItemClickListener<ProductModel> {
            override fun onRemoveClicked(productModel: ProductModel) {
                _adapter.removeItem(productModel)
                _viewModel.removeFromCart(productModel)
            }
        }
        binding.confirmButton.setOnClickListener {

            if (binding.orderNameEditText.text.isEmpty())
                (activity as MainActivity).showToast(getString(R.string.enter_order_name_error))
            else
                _viewModel.submitOrder(binding.orderNameEditText.text.toString())
        }
    }

    private fun observeViewModels() {
        observe(_viewModel.cartListLiveData, ::handleCart)
        observe(_viewModel.showProgressBarLiveData, ::handleProgressBar)
        observe(_viewModel.createCartLiveData, ::handleCreateCartResponse)
        observe(_viewModel.toastLiveData, ::handleShowToast)
    }

    private fun handleShowToast(singleEvent: SingleEvent<String>) {
        singleEvent.getContentIfNotHandled()?.let { (activity as MainActivity).showToast(it) }
    }

    private fun handleCreateCartResponse(singleEvent: SingleEvent<Boolean>) {
        singleEvent.getContentIfNotHandled()?.let {
            if (it) {
                _adapter.clear()
                _viewModel.clearCart()

                binding.orderConfirmedTextView.visibility = View.VISIBLE
                binding.orderNameEditText.visibility = View.GONE
                binding.confirmButton.text = getString(R.string.back_to_products)
                binding.confirmButton.setOnClickListener {

                    activity?.onBackPressed()
                }
            } else
                binding.orderConfirmedTextView.visibility = View.GONE
        }
    }

    private fun handleProgressBar(singleEvent: SingleEvent<Boolean>) {
        singleEvent.getContentIfNotHandled()?.let {
            if (it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.GONE
        }
    }

    private fun handleCart(arrayList: ArrayList<ProductModel>) {
        _adapter.updateProducts(arrayList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                binding.orderConfirmedTextView.visibility = View.GONE
                binding.confirmButton.visibility = View.VISIBLE
                binding.orderNameEditText.visibility = View.VISIBLE
                binding.confirmButton.text = getString(R.string.confirm_order)
                activity?.supportFragmentManager?.popBackStack()
            }
        })
    }
}