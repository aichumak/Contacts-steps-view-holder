package com.example.contacts.presentation.base_adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface BaseViewHolder {

    val viewType: Int

    fun getViewHolder(parent: ViewGroup): ViewHolder

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: BaseItem)
    }
}