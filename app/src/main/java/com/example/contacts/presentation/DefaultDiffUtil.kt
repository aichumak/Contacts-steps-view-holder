package com.example.contacts.presentation

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

abstract class DefaultDiffUtil : DiffUtil.ItemCallback<BaseItem>() {

    override fun areItemsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean = oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean =
        oldItem == newItem

}