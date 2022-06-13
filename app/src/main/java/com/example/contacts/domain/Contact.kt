package com.example.contacts.domain

import com.example.contacts.R
import com.example.contacts.presentation.BaseItem

data class Contact(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: Long,
    val contactImageViewURL: String
) : BaseItem {
    override fun getViewType(): Int {
        return R.layout.fragment_contact
    }
}
