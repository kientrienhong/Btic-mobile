package com.example.bticapplication.feature.admin.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.bticapplication.R
import com.example.bticapplication.databinding.CinemaBrandItemBinding
import com.example.bticapplication.feature.admin.CinemaBrandItemView

class CinemaBrandViewHolder(private val binding: CinemaBrandItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(brand: CinemaBrandItemView) {
        updateBackground(brand.isSelected)
        binding.editIconLayout.setOnClickListener {

        }

        binding.deleteIconLayout.setOnClickListener {

        }

        binding.logo.setOnClickListener {

        }
    }

    private fun updateBackground(isSelected: Boolean) {
        if (isSelected) {
            binding.root.setBackgroundResource(R.drawable.add_cinema_brand_item_background_is_selected)
        } else {
            binding.root.background = null
            binding.root.elevation = 8f
        }
    }

}