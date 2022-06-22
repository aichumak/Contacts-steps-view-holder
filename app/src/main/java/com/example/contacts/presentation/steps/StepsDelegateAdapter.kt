package com.example.contacts.presentation.steps

import com.example.contacts.R
import com.example.contacts.presentation.base_adapter.BaseDiffUtil
import com.example.contacts.presentation.base_adapter.BaseViewHolder
import com.example.contacts.presentation.base_adapter.DelegateAdapter


class StepsDelegateAdapter(
    diffUtilCallback: BaseDiffUtil,
    vararg args: BaseViewHolder
) : DelegateAdapter(diffUtilCallback, *args) {

    override fun getItemCount(): Int = 0

    override fun getItemViewType(position: Int): Int {
        return R.layout.fragment_step_status_line
    }
}
