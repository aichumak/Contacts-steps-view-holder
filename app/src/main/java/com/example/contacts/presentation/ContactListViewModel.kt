package com.example.contacts.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contacts.data.ContactListRepositoryImpl
import com.example.contacts.domain.Contact
import com.example.contacts.domain.GetContactListUseCase
import com.example.contacts.domain.ReplaceContactListForSearchUseCase
import com.example.contacts.domain.UpdateDataBaseLiveDataUseCase
import com.example.contacts.presentation.steps.Step
import java.util.*

class ContactListViewModel : ViewModel() {
    private val repository = ContactListRepositoryImpl
    private val getContactListUseCase = GetContactListUseCase(repository)
    private val updateDataBaseLiveDataUseCase = UpdateDataBaseLiveDataUseCase(repository)
    private val replaceContactListForSearchUseCase = ReplaceContactListForSearchUseCase(repository)

    var savedSearchText = ""

    val contactList = getContactListUseCase.getContactList()

    private val _stepsViewHolderId = MutableLiveData<Step>()
    val stepsViewHolderId: LiveData<Step>
        get() = _stepsViewHolderId

    fun stepId(id: Int) {
        _stepsViewHolderId.value = Step(id)
    }

    fun updateContactList() {
        updateDataBaseLiveDataUseCase.updateDataBaseLiveData()
    }

    fun replaceContactListForSearch(newContactList: TreeSet<Contact>) {
        replaceContactListForSearchUseCase.replaceContactListForSearch(newContactList)
    }
}