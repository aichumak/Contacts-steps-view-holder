package com.example.contacts.presentation

import com.example.contacts.domain.Contact

class ContactDiffCallback : DefaultDiffUtil() {
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