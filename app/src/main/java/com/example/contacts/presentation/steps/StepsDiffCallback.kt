package com.example.contacts.presentation.steps

import com.example.contacts.presentation.base_adapter.BaseDiffUtil
import com.example.contacts.presentation.base_adapter.BaseItem

class StepsDiffCallback : BaseDiffUtil() {
    override fun areItemsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean {
        return super.areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean {
        return super.areContentsTheSame(oldItem, newItem)
    }
}