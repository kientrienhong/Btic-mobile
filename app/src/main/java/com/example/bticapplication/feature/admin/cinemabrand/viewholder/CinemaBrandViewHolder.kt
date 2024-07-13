package com.example.bticapplication.feature.admin.cinemabrand.viewholder

import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bticapplication.R
import com.example.bticapplication.customview.MyConfirmDialog
import com.example.bticapplication.databinding.CinemaBrandItemBinding
import com.example.bticapplication.feature.admin.cinemabrand.CinemaBrandItemView
import com.example.bticapplication.feature.cinemabrand.CinemaBrand

class CinemaBrandViewHolder(
    private val binding: CinemaBrandItemBinding,
    private val fragmentManager: FragmentManager,
    private val setSelectedId: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemView: CinemaBrandItemView, deleteCinemaBrand: suspend (CinemaBrand) -> Unit) {
        updateBackground(itemView.isSelected)
        loadImage(itemView.cinemaBrand.imageUrl)
        binding.deleteIconLayout.setOnClickListener {
            MyConfirmDialog(
                title = "Are you sure to delete cinema brand?",
                confirmButtonLabel = "Yes",
                cancelButtonLabel = "No",
                onConfirmClick = { deleteCinemaBrand(itemView.cinemaBrand) }
            ).show(fragmentManager, "MyConfirmDialog")
        }

        binding.root.setOnClickListener {
            setSelectedId(itemView.cinemaBrand.id)
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

    private fun loadImage(imageUrl: String) {
        Glide
            .with(binding.root.context)
            .load(imageUrl)
            .into(binding.logo)
    }
}