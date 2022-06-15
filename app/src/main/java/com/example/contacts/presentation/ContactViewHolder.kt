package com.example.contacts.presentation

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.contacts.R
import com.example.contacts.domain.Contact
import com.example.contacts.presentation.base_adapter.BaseItem
import com.example.contacts.presentation.base_adapter.BaseViewHolder
import com.example.contacts.presentation.base_adapter.ViewTypes

class ContactViewHolder (val fragmentNavigator: FragmentNavigator?) : BaseViewHolder {

    override val viewType: Int
        get() = ViewTypes.CONTACT_VIEW_HOLDER1.viewType


    override fun getViewHolder(itemView: View) = object : BaseViewHolder.ViewHolder(itemView) {
        val firstName: TextView = itemView.findViewById(R.id.tv_first_name)
        val lastName: TextView = itemView.findViewById(R.id.tv_last_name)
        val phoneNumber: TextView = itemView.findViewById(R.id.tv_phone_number)
        val contactImage: ImageView = itemView.findViewById(R.id.contact_image)

        override fun bind(item: BaseItem) {
            val contact = item as Contact
            firstName.text = contact.firstName
            lastName.text = contact.lastName
            phoneNumber.text = contact.phoneNumber.toString()
            this.itemView.setOnClickListener {
                fragmentNavigator?.goFromContactListFragmentToContactFragment(item.id)
                //viewModel?.savedSearchText = ""
            }

            com.bumptech.glide.Glide.with(this.itemView)
                .load(contact.contactImageViewURL)
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
}
