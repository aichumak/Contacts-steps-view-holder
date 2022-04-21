package com.example.contacts.domain

import androidx.lifecycle.LiveData
import java.util.*

interface ContactListRepository {
    fun editContact(contact: Contact)
    fun getContactList(): LiveData<List<Contact>>
    fun getContact(contactId: Int): Contact
    fun removeContact(contact: Contact)
    fun updateDataBaseLiveData()
    fun replaceContactListForSearch(newContactList: TreeSet<Contact>)
}