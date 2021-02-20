package com.example.capitertask.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capitertask.databinding.ItemNamedOrderBinding

class NamedOrdersAdapter : RecyclerView.Adapter<NamedOrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamedOrderViewHolder {
        val view = ItemNamedOrderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NamedOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: NamedOrderViewHolder, position: Int) {
        holder.asd.nameTextView
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

class NamedOrderViewHolder( val asd: ItemNamedOrderBinding) :
    RecyclerView.ViewHolder(asd.root) {

}
