package com.example.contacts.presentation

import com.example.contacts.domain.Contact
import com.example.contacts.presentation.base_adapter.BaseItem
import com.example.contacts.presentation.base_adapter.BaseDiffUtil

class ContactDiffCallback : BaseDiffUtil() {
    override fun areItemsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean {
        val _oldItem = oldItem as Contact
        val _newItem = newItem as Contact
        return _oldItem.id == _newItem.id
    }

    override fun areContentsTheSame(oldItem: BaseItem, newItem: BaseItem): Boolean {
        val _oldItem = oldItem as Contact
        val _newItem = newItem as Contact
        return _oldItem == _newItem
    }
}