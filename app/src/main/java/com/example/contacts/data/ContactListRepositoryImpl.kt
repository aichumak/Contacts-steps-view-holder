package com.example.contacts.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.contacts.domain.Contact
import com.example.contacts.domain.ContactListRepository
import java.util.*

object ContactListRepositoryImpl : ContactListRepository {

    private var dataBaseLiveData = MutableLiveData<List<Contact>>()
    private var dataBase = sortedSetOf<Contact>({ o1, o2 -> o1.id.compareTo(o2.id) })

    init {
        for (i in 0 until 110) {
            dataBase.add(
                Contact(
                    i,
                    "Mike$i",
                    "Jordan$i",
                    (1452580 + i).toLong(),
                    "https://picsum.photos/id/$i/300/300"

                )
            )
        }
        updateDataBaseLiveData()
    }

    override fun editContact(contact: Contact) {
        val oldContact = getContact(contact.id)
        dataBase.remove(oldContact)
        dataBase.add(contact)
        updateDataBaseLiveData()
    }

    override fun getContactList(): LiveData<List<Contact>> {
        return dataBaseLiveData
    }

    override fun getContact(contactId: Int): Contact {
        return dataBase.find {
            it.id == contactId
        } ?: throw RuntimeException("Contact with id:$contactId not found")
    }

    override fun removeContact(contact: Contact) {
        dataBase.remove(contact)
        updateDataBaseLiveData()
    }

    override fun updateDataBaseLiveData() {
        dataBaseLiveData.value = dataBase.toList()
    }

    override fun replaceContactListForSearch(newContactList: TreeSet<Contact>) {
        dataBaseLiveData.value = newContactList.toList()
    }

}