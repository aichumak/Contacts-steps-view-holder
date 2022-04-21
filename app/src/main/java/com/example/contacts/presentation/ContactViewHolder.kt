package com.example.contacts.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R

class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val firstName: TextView = view.findViewById(R.id.tv_first_name)
    val lastName: TextView = view.findViewById(R.id.tv_last_name)
    val phoneNumber: TextView = view.findViewById(R.id.tv_phone_number)
    val contactImage: ImageView = view.findViewById(R.id.contact_image)
}
