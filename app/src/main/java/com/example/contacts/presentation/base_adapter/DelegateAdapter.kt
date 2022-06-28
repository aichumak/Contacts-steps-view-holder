package com.example.contacts.presentation.base_adapter

import android.view.LayoutInflater
import android.view.ViewGroup

open class DelegateAdapter(
    diffUtilCallback: BaseDiffUtil,
    vararg args: BaseViewHolder
) : androidx.recyclerview.widget.ListAdapter<BaseItem, BaseViewHolder.ViewHolder>(
    diffUtilCallback
) {

    private val viewHolders = args

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder.ViewHolder {
        for (viewHolder in viewHolders) {
            if (viewHolder.viewType == viewType) {
                return viewHolder.getViewHolder(parent)
            }
        }
        throw RuntimeException("Unknown viewType: $viewType")
    }

    override fun onBindViewHolder(holder: BaseViewHolder.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return ViewTypes.getViewType(getItem(position))
    }
}


