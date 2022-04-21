package com.example.contacts.domain

import java.util.*

class ReplaceContactListForSearchUseCase(private val contactListRepository: ContactListRepository) {
    fun replaceContactListForSearch(newContactList: TreeSet<Contact>) {
        contactListRepository.replaceContactListForSearch(newContactList)
    }
}