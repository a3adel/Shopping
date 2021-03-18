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
import com.example.capitertask.presentation.SharedViewModel
import com.example.capitertask.presentation.utils.OnRemoveItemClickListener
import com.example.capitertask.presentation.utils.SingleEvent
import com.example.capitertask.presentation.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private var cartBinding: FragmentCartBinding? = null
    private val adapter = CartProductsAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = cartBinding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val cartViewModel: CartViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cartBinding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModels()
        cartViewModel.getCartItems(sharedViewModel.getSharedList())
    }

    private fun initViews() {
        binding.cartOrdersRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.cartOrdersRecyclerView.adapter = adapter
        adapter.onRemoveItemClickListener = object : OnRemoveItemClickListener<ProductModel> {
            override fun onRemoveClicked(productModel: ProductModel) {
                adapter.removeItem(productModel)
                sharedViewModel.setProductCountToZero(productModel)
            }
        }
        binding.confirmButton.setOnClickListener {

            cartViewModel.submitOrder(
                binding.orderNameEditText.text.toString(),
                cartProducts = cartViewModel.getCart()
            )
        }
        (activity as MainActivity).showCartIcon(false)

    }

    private fun observeViewModels() {
        observe(cartViewModel.cartListLiveData, ::handleCart)
        observe(sharedViewModel.showProgressBarLiveData, ::handleProgressBar)
        observe(cartViewModel.createCartLiveData, ::handleCreateCartResponse)
        observe(sharedViewModel.toastLiveData, ::handleShowToast)
        observe(cartViewModel.toastLiveData, ::handleShowToast)
    }

    private fun handleShowToast(singleEvent: SingleEvent<String>) {
        singleEvent.getContentIfNotHandled()?.let { (activity as MainActivity).showToast(it) }
    }

    private fun handleCreateCartResponse(singleEvent: SingleEvent<Boolean>) {
        singleEvent.getContentIfNotHandled()?.let {
            if (it) {
                adapter.clear()
                sharedViewModel.clearCart()

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

    private fun handleCart(singleEvent: SingleEvent<List<ProductModel>>) {
        singleEvent.getContentIfNotHandled()
            ?.let { productsList -> adapter.updateProducts(productsList) }
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
                (activity as MainActivity).showCartIcon(true)

            }
        })
    }
}