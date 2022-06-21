package com.example.contacts.presentation.steps

import com.example.contacts.presentation.base_adapter.BaseDiffUtil
import com.example.contacts.presentation.base_adapter.BaseItem

class StepsDiffCallback : BaseDiffUtil() {
    override fun areItemsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean {
        val _oldItem = oldItem as Step
        val _newItem = newItem as Step
        return _oldItem.stepId == _newItem.stepId
    }

    override fun areContentsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean {
        val _oldItem = oldItem as Step
        val _newItem = newItem as Step
        return _oldItem == _newItem
    }
}