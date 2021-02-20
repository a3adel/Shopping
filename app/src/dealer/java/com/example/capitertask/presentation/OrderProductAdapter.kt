package com.example.capitertask.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capitertask.databinding.ItemOrderProductBinding
import com.example.capitertask.domain.models.OrderProduct
import com.example.capitertask.domain.utils.CURRENCY

class OrderProductAdapter : RecyclerView.Adapter<OrderProductViewHolder>() {
    private val _orderProducts = ArrayList<OrderProduct>()
    private lateinit var _context: Context
    fun setOrderProducts(products: List<OrderProduct>) {
        _orderProducts.clear()
        _orderProducts.addAll((products))
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderProductViewHolder {
        _context = parent.context
        val view =
            ItemOrderProductBinding.inflate(LayoutInflater.from(_context), parent, false)
        return OrderProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderProductViewHolder, position: Int) {
        val productViewBinder = holder.binder.orderProductProductView.binding
        productViewBinder.nameTextView.text = _orderProducts.get(position).name
        productViewBinder.priceTextView.text = "${_orderProducts.get(position).price.toString()} $CURRENCY"
        productViewBinder.countTextView.text = _orderProducts.get(position).quantity.toString()
        Glide.with(_context).load(_orderProducts.get(position).imageUrl)
            .into(productViewBinder.productImageView)

    }

    override fun getItemCount(): Int {
        return _orderProducts.size
    }
}

class OrderProductViewHolder(val binder: ItemOrderProductBinding) :
    RecyclerView.ViewHolder(binder.root)