package com.example.capitertask.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capitertask.databinding.ItemOrderProductBinding

class OrderProductAdapter:RecyclerView.Adapter<OrderProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderProductViewHolder {
        val view = ItemOrderProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OrderProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderProductViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

class OrderProductViewHolder(val viewBinding:ItemOrderProductBinding):RecyclerView.ViewHolder(viewBinding.root)