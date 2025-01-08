package com.example.mazadytask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mazadytask.R
import com.example.mazadytask.ui.home.OnItemClicked

class GenericAdapter<T>(
    private val itemList: List<T>,
    private val bindView: (View, T, Boolean) -> Unit,
    private val onItemClicked: OnItemClicked
) : RecyclerView.Adapter<GenericAdapter.GenericViewHolder<T>>() {

    class GenericViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view: View = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        return GenericViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_drop_down, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        val item = itemList[position]
        val isLastItem = (position == itemList.size - 1)

        bindView(holder.view, item, isLastItem)
        holder.itemView.setOnClickListener {
            onItemClicked.onItemClick(item)
        }
    }

    override fun getItemCount(): Int = itemList.size
}
