package com.example.capitertask.presentation.products

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capitertask.R
import com.example.capitertask.databinding.ProductItemBinding
import com.example.capitertask.domain.models.Product
import com.example.capitertask.presentation.utils.OnAddItemCartClickListener
import com.example.capitertask.presentation.utils.updateItem

class ProductsAdapter : RecyclerView.Adapter<ProductViewHolder>() {
    private val products = ArrayList<Product>()
    private lateinit var context: Context
    private lateinit var onAddItemToCartClickListener: OnAddItemCartClickListener<Product>

    fun setOnAddItemToCartClickListener(onAddItemToCartClickListener: OnAddItemCartClickListener<Product>) {
        this.onAddItemToCartClickListener = onAddItemToCartClickListener
    }

    fun setProducts(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        context = parent.context
        val view = ProductItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productBinder = holder.productBinding.productView.binding
        val product = products.get(position)
        productBinder.nameTextView.text = product.name
        productBinder.priceTextView.text = "${product.price} ${context.getString(R.string.currency)}"
        productBinder.countTextView.text = product.quantity.toString()
        Glide.with(context).load(products.get(position).imageUrl)
            .into(productBinder.productImageView)

        productBinder.addToCartButton.setOnClickListener {
            onAddItemToCartClickListener.onClick(
                products.get(position)
            )
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateItem(productModel: Product){
        val index = products.indexOf(productModel)
        products.updateItem(productModel)
        notifyItemChanged(index)
    }
}

class ProductViewHolder(val productBinding: ProductItemBinding) :
    RecyclerView.ViewHolder(productBinding.root) {

}