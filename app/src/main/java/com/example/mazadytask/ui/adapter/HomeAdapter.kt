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
    private val onItemClicked: OnItemClicked,
    private val isSecondLayout: Boolean = false
) : RecyclerView.Adapter<GenericAdapter.GenericViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (isSecondLayout) 2 else 1
    }

    class GenericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view: View = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val layoutId = if (viewType == 1) R.layout.list_item_drop_down // First layout
        else R.layout.selectable_container // Second layout
        return GenericViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val item = itemList[position]
        val isLastItem = (position == itemList.size - 1)

        bindView(holder.view, item, isLastItem)
        holder.itemView.setOnClickListener {
            onItemClicked.onItemClick(item)
        }
    }

    override fun getItemCount(): Int = itemList.size
}
