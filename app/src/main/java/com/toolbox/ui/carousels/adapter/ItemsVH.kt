package com.toolbox.ui.carousels.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.toolbox.databinding.ItemItemsBinding
import com.toolbox.models.carousels.Items
import com.toolbox.utils.Utils

class ItemsVH(
    private val binding: ItemItemsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(item: Items, type: String) {
        if (type == "poster") {
            binding.tvTitlePoster.visibility = View.VISIBLE
            binding.tvTitleThumb.visibility = View.GONE
        } else {
            binding.tvTitlePoster.visibility = View.GONE
            binding.tvTitleThumb.visibility = View.VISIBLE
        }
        binding.tvTitlePoster.text = item.title
        binding.tvTitleThumb.text = item.title
        val url = item.imageUrl!!.removeRange(0, 4)
        val newUrl = "https$url"
        Utils.loadImageUrl(newUrl, binding.ivItem)
    }

}