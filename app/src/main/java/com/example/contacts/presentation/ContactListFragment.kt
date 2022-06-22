package com.example.contacts.presentation

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.R
import com.example.contacts.databinding.FragmentContactListBinding
import com.example.contacts.domain.Contact
import com.example.contacts.presentation.base_adapter.BaseAdapter
import com.example.contacts.presentation.base_adapter.DelegateAdapter
import com.example.contacts.presentation.steps.Step
import com.example.contacts.presentation.steps.StepsDelegateAdapter
import com.example.contacts.presentation.steps.StepsDiffCallback
import com.example.contacts.presentation.steps.StepsViewHolder
import java.util.*

class ContactListFragment : Fragment(R.layout.fragment_contact_list) {
    private var binding: FragmentContactListBinding? = null
    private var fragmentNavigator: FragmentNavigator? = null
    private var clickListener: ClickListener? = null
    private var viewModel: ContactListViewModel? = null
    //private var contactListAdapter: ContactListAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigator) fragmentNavigator = context
        if (context is ClickListener) clickListener = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ContactListViewModel::class.java]

        val baseAdapter = BaseAdapter

        val delegateAdapter = DelegateAdapter(
            ContactDiffCallback(),
            ContactViewHolder(fragmentNavigator),
            ContactViewHolder2(fragmentNavigator)
        )

        val headerDelegateAdapter =
            StepsDelegateAdapter(StepsDiffCallback(), StepsViewHolder(requireContext(), 7, true))
        //val headerDelegateAdapter = DelegateAdapter(StepsDiffCallback(), StepsViewHolder(5, 0, true))
        baseAdapter.addDelegateAdapter(headerDelegateAdapter)
        //baseAdapter.addDelegateAdapter(delegateAdapter)

        binding?.let {
            it.rvContactList.layoutManager = LinearLayoutManager(context)
            it.rvContactList.adapter = baseAdapter.getBaseAdapter()
            val divider = ContextCompat.getDrawable(view.context, R.drawable.item_decoration)
            divider?.let { itDivider ->
                it.rvContactList.addItemDecoration(ItemDecoration(itDivider))
            }
            viewModel?.stepId(0)
            it.button2.setOnClickListener {
                viewModel?.stepId(viewModel?.stepsViewHolderId?.value?.stepId?.plus(1) ?: 0)
            }
            it.button1.setOnClickListener {
                viewModel?.stepId(viewModel?.stepsViewHolderId?.value?.stepId?.minus(1) ?: 0)
            }
        }
        viewModel?.contactList?.observe(viewLifecycleOwner) {
            delegateAdapter.submitList(it)
        }
        viewModel?.stepsViewHolderId?.observe(viewLifecycleOwner){
            headerDelegateAdapter.submitList(listOf(it))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.search_action)
        val searchView = searchItem.actionView as SearchView

        viewModel?.let {
            if (it.savedSearchText.isNotEmpty()) {
                searchItem.expandActionView()
                searchView.setQuery(viewModel?.savedSearchText as CharSequence, true)
            }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    it.updateContactList()
                    val contactList = it.contactList
                    val searchText = p0?.lowercase(Locale.getDefault()) ?: ""
                    val newContactList =
                        sortedSetOf<Contact>({ o1, o2 -> o1.id.compareTo(o2.id) })

                    if (searchText.isNotEmpty()) {
                        contactList.value?.forEach { itValue ->
                            if (itValue.firstName.lowercase(Locale.getDefault())
                                    .contains(searchText) ||
                                itValue.lastName.lowercase(Locale.getDefault()).contains(searchText)
                            ) {
                                newContactList.add(itValue)
                            }
                        }
                        it.replaceContactListForSearch(newContactList)
                        it.savedSearchText = searchText
                    }
                    return false
                }
            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {
        val FRAGMENT_CONTACT_LIST = "FRAGMENT_CONTACT_LIST"
        fun newInstance() = ContactListFragment()
    }
}