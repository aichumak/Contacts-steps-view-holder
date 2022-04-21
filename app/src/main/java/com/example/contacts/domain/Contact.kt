package com.example.contacts.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: Long,
    val contactImageViewURL: String
) : Parcelable
