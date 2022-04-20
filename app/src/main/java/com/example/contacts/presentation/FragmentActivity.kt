package com.example.contacts.presentation

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.R
import com.example.contacts.data.ContactListRepositoryImpl
import com.example.contacts.databinding.ActivityFragmentBinding
import com.example.contacts.domain.GetContactUseCase
import com.example.contacts.domain.RemoveContactUseCase

class FragmentActivity : AppCompatActivity(), FragmentNavigator, ClickListener {
    private lateinit var binding: ActivityFragmentBinding
    private val repository = ContactListRepositoryImpl
    private val removeContactUseCase = RemoveContactUseCase(repository)
    private val getContactUseCase = GetContactUseCase(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportFragmentManager.backStackEntryCount == 0) {
            supportFragmentManager.beginTransaction().run {
                val fragment = ContactListFragment.newInstance()
                replace(
                    R.id.fragment_container,
                    fragment,
                    ContactListFragment.FRAGMENT_CONTACT_LIST
                )
                addToBackStack(ContactListFragment.FRAGMENT_CONTACT_LIST)
                commit()
            }
        } else {
            val index = supportFragmentManager.backStackEntryCount - 1
            val backEntry = supportFragmentManager.getBackStackEntryAt(index)
            val tag = backEntry.name
            supportFragmentManager.popBackStack(tag, 0)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        } else {
            goFromContactFragmentToContactListFragment()
        }
    }

    override fun goFromContactListFragmentToContactFragment(contactId: Int) {
        val fragmentContainer = if (binding.fragmentContainerForTablet == null) {
            R.id.fragment_container
        } else {
            R.id.fragment_container_for_tablet
        }
        supportFragmentManager.beginTransaction().run {
            val fragment = ContactFragment()
            fragment.arguments = Bundle().apply {
                putInt(ContactFragment.CONTACT_ID, contactId)
            }
            replace(fragmentContainer, fragment, ContactFragment.FRAGMENT_CONTACT_TAG)
            addToBackStack(ContactFragment.FRAGMENT_CONTACT_TAG)
            commit()
        }
    }

    override fun goFromContactFragmentToContactListFragment() {
        supportFragmentManager.popBackStack(ContactListFragment.FRAGMENT_CONTACT_LIST, 0)
    }

    override fun removeContact(position: Int) {
        val listener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> removeItem(position)
                DialogInterface.BUTTON_NEGATIVE -> Toast.makeText(
                    this,
                    R.string.remove_canceled,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val dialog = AlertDialog.Builder(this)
            .setCancelable(false)
            .setIcon(R.mipmap.ic_launcher_round)
            .setTitle(R.string.confirm_dialog_title)
            .setMessage(R.string.confirm_dialog_message)
            .setPositiveButton(R.string.confirm_dialog_positive_button, listener)
            .setNegativeButton(R.string.confirm_dialog_negative_button, listener)
            .create()
        dialog.show()
    }

    private fun removeItem(position: Int) {
        removeContactUseCase.removeContact(getContactUseCase.getContact(position))
        goFromContactFragmentToContactListFragment()
    }
}