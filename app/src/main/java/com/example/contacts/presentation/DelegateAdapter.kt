package com.example.contacts.presentation

import android.view.LayoutInflater
import android.view.ViewGroup

class DelegateAdapter(
    diffUtilCallback: DefaultDiffUtil,
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
                val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
                return viewHolder.getViewHolder(view)
            }
        }
        throw RuntimeException("Unknown viewType: $viewType")
    }

    override fun onBindViewHolder(holder: BaseViewHolder.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getViewType()
    }
}


