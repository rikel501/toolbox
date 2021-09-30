package com.toolbox.ui.carousels.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toolbox.databinding.ItemItemsBinding
import com.toolbox.models.carousels.Items

class ItemsAdapter(
    private val type: String,
    private val items: MutableList<Items>
) : RecyclerView.Adapter<ItemsVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsVH {
        val binding = ItemItemsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemsVH(binding)
    }

    override fun onBindViewHolder(holder: ItemsVH, position: Int) =
        holder.bindData(items[position], type)

    override fun getItemCount(): Int = items.size

}