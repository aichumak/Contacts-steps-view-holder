package com.example.contacts.presentation.base_adapter

import androidx.recyclerview.widget.ConcatAdapter

object BaseAdapter {

    private val concatAdapter = ConcatAdapter()

    fun getBaseAdapter() = concatAdapter

    fun addDelegateAdapter(delegateAdapter: DelegateAdapter){
        concatAdapter.addAdapter(delegateAdapter)
    }

    fun removeDelegateAdapter(delegateAdapter: DelegateAdapter) {
        concatAdapter.removeAdapter(delegateAdapter)
    }

    fun removeAllDelegateAdapters() {
        val adaptersList = concatAdapter.adapters
        adaptersList.forEach {
            concatAdapter.removeAdapter(it)
        }
    }
}