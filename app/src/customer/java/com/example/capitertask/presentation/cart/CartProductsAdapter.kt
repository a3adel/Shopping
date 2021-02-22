package com.example.capitertask.presentation.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capitertask.databinding.ItemCartProductBinding
import com.example.capitertask.domain.models.ProductModel
import com.example.capitertask.domain.utils.CURRENCY
import com.example.capitertask.presentation.utils.OnRemoveItemClickListener

class CartProductsAdapter : RecyclerView.Adapter<CartViewHolder>() {
    private val _products = ArrayList<ProductModel>()
    private lateinit var _context: Context
    lateinit var onRemoveItemClickListener: OnRemoveItemClickListener<ProductModel>
    fun updateProducts(products: List<ProductModel>) {
        _products.clear()
        _products.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        _context = parent.context
        val binder = ItemCartProductBinding.inflate(LayoutInflater.from(_context), parent, false)
        return CartViewHolder(binder)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val productView = holder.binder.cartProductView
        val product = _products.get(position)
        productView.binding.nameTextView.text = product.name
        productView.binding.countTextView.text = product.amount.toString()
        productView.binding.priceTextView.text = "${product.price.toString()} $CURRENCY"
        productView.binding.deleteFromCartImageView.setOnClickListener {
            onRemoveItemClickListener.onRemoveClicked(product)
        }
        Glide.with(_context).load(product.imageUrl).into(productView.binding.productImageView)
    }

    override fun getItemCount(): Int = _products.size

    fun removeItem(productModel: ProductModel) {
        val index = _products.indexOf(productModel)
        if (_products.remove(productModel))
            notifyItemRemoved(index)
    }

    fun clear() {
        _products.clear()
        notifyDataSetChanged()
    }
}

class CartViewHolder(val binder: ItemCartProductBinding) : RecyclerView.ViewHolder(binder.root) {

}
