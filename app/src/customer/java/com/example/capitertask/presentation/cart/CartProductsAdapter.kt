package com.example.capitertask.presentation.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capitertask.R
import com.example.capitertask.databinding.ItemCartProductBinding
import com.example.capitertask.domain.models.Product
import com.example.capitertask.presentation.utils.OnRemoveItemClickListener

class CartProductsAdapter : RecyclerView.Adapter<CartProductsAdapter.CartViewHolder>() {
    private val products = ArrayList<Product>()
    private lateinit var context: Context
    lateinit var onRemoveItemClickListener: OnRemoveItemClickListener<Product>
    fun updateProducts(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        context = parent.context
        val binder = ItemCartProductBinding.inflate(LayoutInflater.from(context), parent, false)
        return CartViewHolder(binder)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    fun removeItem(productModel: Product) {
        val index = products.indexOf(productModel)
        if (products.remove(productModel))
            notifyItemRemoved(index)
    }

    fun clear() {
        products.clear()
        notifyDataSetChanged()
    }

    inner class CartViewHolder(private val binder: ItemCartProductBinding) :
        RecyclerView.ViewHolder(binder.root) {
        fun bind(product: Product) {
            val productView = binder.cartProductView

            productView.binding.nameTextView.text = product.name
            productView.binding.countTextView.text = product.quantity.toString()
            productView.binding.priceTextView.text = "${product.price} ${context.getString(R.string.currency)}"
            productView.binding.deleteFromCartImageView.setOnClickListener {
                onRemoveItemClickListener.onRemoveClicked(product)
            }
            Glide.with(context).load(product.imageUrl).into(productView.binding.productImageView)
        }
    }
}


