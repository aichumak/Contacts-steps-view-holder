package com.example.contacts.presentation

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.contacts.R
import com.example.contacts.databinding.FragmentContactEditableBinding
import com.example.contacts.domain.Contact

class ContactFragment : Fragment(R.layout.fragment_contact_editable) {
    private var binding: FragmentContactEditableBinding? = null
    private var viewModel: ContactViewModel? = null
    private var fragmentNavigator: FragmentNavigator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigator) fragmentNavigator = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactEditableBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        binding?.let { itBinding ->
            arguments?.let {
                val contact = viewModel?.getContact(it.getInt(CONTACT_ID))
                itBinding.etFirstName.setText(contact?.firstName)
                itBinding.etLastName.setText(contact?.lastName)
                itBinding.etPhoneNumber.setText(contact?.phoneNumber.toString())
                itBinding.saveButton.setOnClickListener {
                    if (contact != null) {
                        //val ab = itBinding.etPhoneNumber.text
                        viewModel?.editContact(

                            Contact(
                                contact.id,
                                itBinding.etFirstName.text.toString(),
                                itBinding.etLastName.text.toString(),
                                itBinding.etPhoneNumber.text.toString().toLong(),
                                contact.contactImageViewURL
                            )
                        )
                        fragmentNavigator?.goFromContactFragmentToContactListFragment()
                    }
                }

                Glide.with(this)
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
                    .into(itBinding.contactImage)
            }
        }
    }

    companion object {
        const val CONTACT_ID = "CONTACT_ID"
        const val FRAGMENT_CONTACT_TAG = "FRAGMENT_CONTACT_TAG"

        fun newInstance(contactId: Int): ContactFragment {
            val args = Bundle().apply {
                putInt(CONTACT_ID, contactId)
            }
            val fragment = ContactFragment()
            fragment.arguments = args
            return fragment
        }
    }
}