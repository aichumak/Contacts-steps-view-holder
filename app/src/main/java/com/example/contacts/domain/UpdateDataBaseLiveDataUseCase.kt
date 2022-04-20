package com.example.contacts.domain

class UpdateDataBaseLiveDataUseCase(private val contactListRepository: ContactListRepository) {
    fun updateDataBaseLiveData() {
        contactListRepository.updateDataBaseLiveData()
    }
}