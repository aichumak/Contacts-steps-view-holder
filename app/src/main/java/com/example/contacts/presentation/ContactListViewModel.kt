package com.example.contacts.presentation

import androidx.lifecycle.ViewModel
import com.example.contacts.data.ContactListRepositoryImpl
import com.example.contacts.domain.Contact
import com.example.contacts.domain.GetContactListUseCase
import com.example.contacts.domain.ReplaceContactListForSearchUseCase
import com.example.contacts.domain.UpdateDataBaseLiveDataUseCase
import java.util.*

class ContactListViewModel : ViewModel() {
    private val repository = ContactListRepositoryImpl
    private val getContactListUseCase = GetContactListUseCase(repository)
    private val updateDataBaseLiveDataUseCase = UpdateDataBaseLiveDataUseCase(repository)
    private val replaceContactListForSearchUseCase = ReplaceContactListForSearchUseCase(repository)

    val contactList = getContactListUseCase.getContactList()

    fun updateContactList() {
        updateDataBaseLiveDataUseCase.updateDataBaseLiveData()
    }

    fun replaceContactListForSearch(newContactList: TreeSet<Contact>) {
        replaceContactListForSearchUseCase.replaceContactListForSearch(newContactList)
    }
}