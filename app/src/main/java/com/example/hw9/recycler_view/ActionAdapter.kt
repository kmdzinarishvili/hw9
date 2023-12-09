package com.example.hw9.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw9.R

class ActionAdapter(private val dataList: List<ActionItem>) : RecyclerView.Adapter<ActionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ActionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ActionViewHolder, position: Int) {
        val currentItem = dataList[position]

        holder.actionTypeTextView.text = currentItem.actionType.toString()
        holder.actionStateTextView.text = currentItem.actionState.toString()
        holder.actionTimeTextView.text = currentItem.actionTime.toString()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}