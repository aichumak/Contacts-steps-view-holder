package com.example.contacts.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface BaseViewHolder {

    val viewType: Int

    fun getViewHolder(itemView: View): ViewHolder

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: BaseItem)
    }
}