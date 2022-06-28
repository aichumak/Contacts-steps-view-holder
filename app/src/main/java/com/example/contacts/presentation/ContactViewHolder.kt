package com.example.contacts.presentation

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.contacts.databinding.FragmentContactBinding
import com.example.contacts.domain.Contact
import com.example.contacts.presentation.base_adapter.BaseItem
import com.example.contacts.presentation.base_adapter.BaseViewHolder
import com.example.contacts.presentation.base_adapter.ViewTypes

class ContactViewHolder(val fragmentNavigator: FragmentNavigator?) : BaseViewHolder {

    override val viewType: Int
        get() = ViewTypes.CONTACT_VIEW_HOLDER1.viewType

    override fun getViewHolder(parent: ViewGroup): BaseViewHolder.ViewHolder {

        val binding =
            FragmentContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return object : BaseViewHolder.ViewHolder(binding.root) {


            override fun bind(item: BaseItem) {
                val contact = item as Contact
                binding.tvFirstName.text = contact.firstName
                binding.tvLastName.text = contact.lastName
                binding.tvPhoneNumber.text = contact.phoneNumber.toString()
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
                    .into(binding.contactImage)
            }
        }
    }
}
