package com.example.bticapplication.feature.admin.cinema.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.bticapplication.databinding.CinemaItemBinding
import com.example.bticapplication.feature.cinema.Cinema

class CinemaViewHolder(private val binding: CinemaItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(cinema: Cinema) {
        binding.name.text = cinema.name
    }
}