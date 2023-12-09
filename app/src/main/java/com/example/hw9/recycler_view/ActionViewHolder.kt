package com.example.hw9.recycler_view
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hw9.R

class ActionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val actionTypeTextView: TextView = itemView.findViewById(R.id.actionTypeTextView)
    val actionStateTextView: TextView = itemView.findViewById(R.id.actionStateTextView)
    val actionTimeTextView: TextView = itemView.findViewById(R.id.actionTimeTextView)
}
