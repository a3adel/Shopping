package com.example.capitertask.presentation.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capitertask.databinding.ProductItemBinding
import com.example.capitertask.domain.models.ProductModel

class ProductsAdapter : RecyclerView.Adapter<ProductViewHolder>() {
    private val _products = ArrayList<ProductModel>()
    fun setProducts(products: List<ProductModel>) {
        _products.clear()
        _products.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return _products.size
    }
}

class ProductViewHolder(itemView: ProductItemBinding) : RecyclerView.ViewHolder(itemView.root) {

}