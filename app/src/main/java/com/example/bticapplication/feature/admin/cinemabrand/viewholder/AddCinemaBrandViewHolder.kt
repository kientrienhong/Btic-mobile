package com.example.bticapplication.feature.admin.cinemabrand.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bticapplication.databinding.AddCinemaBrandItemBinding

class AddCinemaBrandViewHolder(
    binding: AddCinemaBrandItemBinding,
    showAddCinemaBrand: () -> Unit
) : ViewHolder(binding.root) {
    init {
        itemView.setOnClickListener {
            showAddCinemaBrand()
        }
    }
}