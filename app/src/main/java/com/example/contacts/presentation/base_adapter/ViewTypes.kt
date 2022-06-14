package com.example.contacts.presentation.base_adapter

import com.example.contacts.R
import com.example.contacts.domain.Contact

enum class ViewTypes(val viewType: Int) {
    CONTACT_VIEW_HOLDER1(R.layout.fragment_contact),
    CONTACT_VIEW_HOLDER2(R.layout.fragment_contact2);

    companion object {
        fun getViewType(item: Any): Int {
            when (item) {
                is Contact -> {
                    return if (item.id % 2 == 0) {
                        CONTACT_VIEW_HOLDER1.viewType
                    } else {
                        CONTACT_VIEW_HOLDER2.viewType
                    }
                }
            }
            return 0
        }
    }
}



