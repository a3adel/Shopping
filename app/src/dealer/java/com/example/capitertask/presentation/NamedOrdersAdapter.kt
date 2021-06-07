package com.example.capitertask.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capitertask.databinding.ItemNamedOrderBinding
import com.example.capitertask.domain.models.NamedOrder


class NamedOrdersAdapter : RecyclerView.Adapter<NamedOrdersAdapter.NamedOrderViewHolder>() {
    private val namedOrders = ArrayList<NamedOrder>()
    private lateinit var context: Context
    fun setNamedOrders(namedOrders: List<NamedOrder>) {
        this.namedOrders.clear()
        this.namedOrders.addAll(namedOrders)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamedOrderViewHolder {
        context = parent.context
        val view = ItemNamedOrderBinding.inflate(LayoutInflater.from(context), parent, false)
        return NamedOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: NamedOrderViewHolder, position: Int) {

        holder.bind(namedOrders[position])
    }

    override fun getItemCount(): Int {
        return namedOrders.size
    }

    inner class NamedOrderViewHolder(val binder: ItemNamedOrderBinding) :
        RecyclerView.ViewHolder(binder.root) {
        fun bind(order: NamedOrder) {
            binder.nameTextView.text = order.name
            val adapter = OrderProductAdapter()

            order.products?.let {
                val linearLayoutManager = LinearLayoutManager(context)
                binder.productsRecyclerView.layoutManager = linearLayoutManager
                val dividerItemDecoration = DividerItemDecoration(
                    context,
                    linearLayoutManager.getOrientation()
                )
                binder.productsRecyclerView.addItemDecoration(dividerItemDecoration)
                binder.productsRecyclerView.adapter = adapter
                adapter.setOrderProducts(it)

            }
        }

    }
}


