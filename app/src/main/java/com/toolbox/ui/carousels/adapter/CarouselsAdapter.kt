package com.toolbox.ui.carousels.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toolbox.databinding.ItemCarouselsBinding
import com.toolbox.models.carousels.ResponseCarousels

class CarouselsAdapter : RecyclerView.Adapter<CarouselsVH>() {

    private val carousels = mutableListOf<ResponseCarousels>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselsVH {
        val binding = ItemCarouselsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CarouselsVH(binding)
    }

    override fun onBindViewHolder(holder: CarouselsVH, position: Int) =
        holder.bindData(carousels[position])

    override fun getItemCount(): Int = carousels.size

    fun setMoreData(it: MutableList<ResponseCarousels>?) {
        carousels.addAll(it!!)
        notifyDataSetChanged()
    }

}