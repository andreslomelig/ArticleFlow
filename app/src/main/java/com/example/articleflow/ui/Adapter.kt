package com.example.articleflow.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.articleflow.data.Product
import com.example.articleflow.databinding.ItemProductBinding

class ProductAdapter(
    private val onDelete: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.VH>() {

    private val items = mutableListOf<Product>()

    fun submitList(list: List<Product>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class VH(val b: ItemProductBinding): RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = items[position]
        holder.b.tvName.text = p.name
        holder.b.tvPriceQty.text = "Precio: $${p.price}  ·  Cant.: ${p.quantity}"
        holder.b.btnDelete.setOnClickListener { onDelete(p) }
    }

    override fun getItemCount(): Int = items.size
}
