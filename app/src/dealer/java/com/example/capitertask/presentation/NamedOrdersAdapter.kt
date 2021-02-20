package com.example.capitertask.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capitertask.databinding.ItemNamedOrderBinding
import com.example.capitertask.domain.models.NamedOrder


class NamedOrdersAdapter : RecyclerView.Adapter<NamedOrderViewHolder>() {
    private val _namedOrders = ArrayList<NamedOrder>()
    private lateinit var _context: Context
    fun setNamedOrders(namedOrders: List<NamedOrder>) {
        _namedOrders.clear()
        _namedOrders.addAll(namedOrders)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamedOrderViewHolder {
        _context = parent.context
        val view = ItemNamedOrderBinding.inflate(LayoutInflater.from(_context), parent, false)
        return NamedOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: NamedOrderViewHolder, position: Int) {
        val binder = holder.binder
        binder.nameTextView.text = _namedOrders.get(position).name
        val adapter = OrderProductAdapter()

        _namedOrders.get(position).products?.let {
            val linearLayoutManager = LinearLayoutManager(_context)
            binder.productsRecyclerView.layoutManager = linearLayoutManager
            val dividerItemDecoration = DividerItemDecoration(
                _context,
                linearLayoutManager.getOrientation()
            )
            binder.productsRecyclerView.addItemDecoration(dividerItemDecoration)
            binder.productsRecyclerView.adapter = adapter
            adapter.setOrderProducts(it)

        }


    }

    override fun getItemCount(): Int {
        return _namedOrders.size
    }
}

class NamedOrderViewHolder(val binder: ItemNamedOrderBinding) :
    RecyclerView.ViewHolder(binder.root) {

}
