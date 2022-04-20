package com.example.contacts.domain

class RemoveContactUseCase(private val contactListRepository: ContactListRepository) {
    fun removeContact(contact: Contact) {
        contactListRepository.removeContact(contact)
    }
}