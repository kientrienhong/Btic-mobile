package com.example.bticapplication.feature.admin.cinema

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.bticapplication.databinding.CinemaItemBinding
import com.example.bticapplication.feature.admin.cinema.viewholder.CinemaViewHolder
import com.example.bticapplication.feature.cinema.Cinema

class CinemaAdapter : Adapter<CinemaViewHolder>() {

    private val diff = AsyncListDiffer(this, object : ItemCallback<Cinema>() {
        override fun areItemsTheSame(oldItem: Cinema, newItem: Cinema): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Cinema, newItem: Cinema): Boolean =
            oldItem == newItem
    })

    private val currentList: List<Cinema>
        get() = diff.currentList

    fun submitList(list: List<Cinema>) {
        diff.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder =
        CinemaViewHolder(
            CinemaItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        val cinema = currentList[position]
        holder.bind(cinema)
    }

    override fun getItemCount(): Int = currentList.size
}