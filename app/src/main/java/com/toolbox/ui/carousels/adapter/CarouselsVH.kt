package com.toolbox.ui.carousels.adapter

import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.toolbox.databinding.ItemCarouselsBinding
import com.toolbox.models.carousels.ResponseCarousels

class CarouselsVH(
    private val binding: ItemCarouselsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(carousels: ResponseCarousels) {
        binding.tvTitle.text = carousels.title
        binding.rvItems.layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
        val adapter = ItemsAdapter(carousels.type!!, carousels.items!!)
        binding.rvItems.adapter = adapter
    }

}