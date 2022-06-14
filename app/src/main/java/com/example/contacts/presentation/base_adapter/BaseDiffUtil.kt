package com.example.contacts.presentation.base_adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.contacts.presentation.base_adapter.BaseItem

abstract class BaseDiffUtil : DiffUtil.ItemCallback<BaseItem>() {

    override fun areItemsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean = oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean =
        oldItem == newItem

}