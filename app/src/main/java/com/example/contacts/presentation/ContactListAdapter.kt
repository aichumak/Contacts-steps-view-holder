package com.example.contacts.presentation

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.contacts.R
import com.example.contacts.data.ContactListRepositoryImpl
import com.example.contacts.domain.Contact
import com.example.contacts.domain.GetContactListUseCase

class ContactListAdapter(
    private val viewModel: ContactListViewModel? = null,
    private val fragmentNavigator: FragmentNavigator? = null,
    private val clickListener: ClickListener? = null
) : androidx.recyclerview.widget.ListAdapter<Contact, ContactViewHolder>(ContactDiffCallback()) {
    private val repository = ContactListRepositoryImpl
    private val getContactListUseCase = GetContactListUseCase(repository)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val contactView =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_contact, parent, false)
        return ContactViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        with(holder) {
            firstName.text = contact.firstName
            lastName.text = contact.lastName
            phoneNumber.text = contact.phoneNumber.toString()
            itemView.setOnClickListener {
                fragmentNavigator?.goFromContactListFragmentToContactFragment(contact.id)
                viewModel?.savedSearchText = ""
            }
            itemView.setOnLongClickListener {
                clickListener?.removeContact(contact.id)
                true
            }

            Glide.with(this.itemView)
                .load(contact?.contactImageViewURL)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(contactImage)
        }
    }

    override fun getItemCount(): Int {
        return getContactListUseCase.getContactList().value?.size ?: 0
    }
}