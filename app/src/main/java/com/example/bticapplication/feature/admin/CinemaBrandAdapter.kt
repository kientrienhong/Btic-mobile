package com.example.bticapplication.feature.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bticapplication.databinding.AddCinemaBrandItemBinding
import com.example.bticapplication.databinding.CinemaBrandItemBinding
import com.example.bticapplication.feature.admin.viewholder.AddCinemaBrandViewHolder
import com.example.bticapplication.feature.admin.viewholder.CinemaBrandViewHolder

class CinemaBrandAdapter(
    private val showAddCinemaBrand: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diff = AsyncListDiffer(this, cinemaBrandDiffItemCallback)

    fun submit(list: List<CinemaBrandItemView>): Unit = diff.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            CinemaBrandType.ADD.ordinal -> AddCinemaBrandViewHolder(
                AddCinemaBrandItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                showAddCinemaBrand
            )

            else -> CinemaBrandViewHolder(
                CinemaBrandItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // AddCinemaBrandViewHolder is at position 0
        if (position == 0) {
            return
        }
        val item = diff.currentList[position - OFFSET_FOR_ADD_VIEW]
        when (holder) {
            is CinemaBrandViewHolder -> holder.bind(item)
            is AddCinemaBrandViewHolder -> Unit
        }
    }

    override fun getItemCount(): Int = diff.currentList.size + OFFSET_FOR_ADD_VIEW

    override fun getItemViewType(position: Int): Int = if (position == 0) {
        CinemaBrandType.ADD.ordinal
    } else {
        CinemaBrandType.NORMAL.ordinal
    }

    companion object {
        private const val OFFSET_FOR_ADD_VIEW = 1


        private val cinemaBrandDiffItemCallback =
            object : DiffUtil.ItemCallback<CinemaBrandItemView>() {
                override fun areItemsTheSame(
                    oldItem: CinemaBrandItemView,
                    newItem: CinemaBrandItemView
                ): Boolean = oldItem.cinemaBrand.id == newItem.cinemaBrand.id

                override fun areContentsTheSame(
                    oldItem: CinemaBrandItemView,
                    newItem: CinemaBrandItemView
                ): Boolean = oldItem.cinemaBrand == newItem.cinemaBrand
            }
    }
}

enum class CinemaBrandType {
    NORMAL,
    ADD
}