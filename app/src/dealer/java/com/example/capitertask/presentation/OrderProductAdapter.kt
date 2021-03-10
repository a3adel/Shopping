package com.example.capitertask.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capitertask.databinding.ItemOrderProductBinding
import com.example.capitertask.domain.models.OrderProduct
import com.example.capitertask.domain.utils.CURRENCY

class OrderProductAdapter : RecyclerView.Adapter<OrderProductAdapter.OrderProductViewHolder>() {
    private val orderProducts = ArrayList<OrderProduct>()
    private lateinit var context: Context
    fun setOrderProducts(products: List<OrderProduct>) {
        orderProducts.clear()
        orderProducts.addAll((products))
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderProductViewHolder {
        context = parent.context
        val view =
            ItemOrderProductBinding.inflate(LayoutInflater.from(context), parent, false)
        return OrderProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderProductViewHolder, position: Int) {
        holder.bind(orderProducts[position])
    }

    override fun getItemCount(): Int = orderProducts.size


    inner class OrderProductViewHolder(val binder: ItemOrderProductBinding) :
        RecyclerView.ViewHolder(binder.root) {
        fun bind(order: OrderProduct) {
            val productViewBinder = binder.orderProductProductView.binding
            productViewBinder.nameTextView.text = order.name
            productViewBinder.priceTextView.text =
                "${order.price.toString()} $CURRENCY"
            productViewBinder.countTextView.text = order.quantity.toString()
            Glide.with(context).load(order.imageUrl)
                .into(productViewBinder.productImageView)
        }
    }
}

