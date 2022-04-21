package com.example.contacts.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.contacts.domain.Contact

class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldContact: Contact, newContact: Contact): Boolean {
        return oldContact.id == newContact.id
    }

    override fun areContentsTheSame(oldContact: Contact, newContact: Contact): Boolean {
        return oldContact == newContact
    }
}