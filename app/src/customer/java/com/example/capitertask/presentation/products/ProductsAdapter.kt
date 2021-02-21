package com.example.capitertask.presentation.products

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capitertask.databinding.ProductItemBinding
import com.example.capitertask.domain.models.ProductModel
import com.example.capitertask.domain.utils.CURRENCY
import com.example.capitertask.presentation.OnAddItemCartClickListener

class ProductsAdapter : RecyclerView.Adapter<ProductViewHolder>() {
    private val _products = ArrayList<ProductModel>()
    private lateinit var _context: Context
    private lateinit var _onAddItemToCartClickListener: OnAddItemCartClickListener

    fun setOnAddItemToCartClickListener(onAddItemToCartClickListener: OnAddItemCartClickListener) {
        _onAddItemToCartClickListener = onAddItemToCartClickListener
    }

    fun setProducts(products: List<ProductModel>) {
        _products.clear()
        _products.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        _context = parent.context
        val view = ProductItemBinding.inflate(LayoutInflater.from(_context), parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productBinder = holder.productBinding.productView.binding
        productBinder.nameTextView.text = _products.get(position).name
        productBinder.priceTextView.text = "${_products.get(position).price.toString()} $CURRENCY"
        Glide.with(_context).load(_products.get(position).imageUrl)
            .into(productBinder.productImageView)
        productBinder.addToCartButton.setOnClickListener {
            _onAddItemToCartClickListener.onClick(
                _products.get(position)
            )
        }
    }

    override fun getItemCount(): Int {
        return _products.size
    }
}

class ProductViewHolder(val productBinding: ProductItemBinding) :
    RecyclerView.ViewHolder(productBinding.root) {

}