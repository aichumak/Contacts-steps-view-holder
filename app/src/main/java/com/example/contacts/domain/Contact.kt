package com.example.contacts.domain

import com.example.contacts.presentation.base_adapter.BaseItem

data class Contact(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: Long,
    val contactImageViewURL: String
) : BaseItem
