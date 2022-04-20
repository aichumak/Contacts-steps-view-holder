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
import java.util.*

class ContactListFragment : Fragment(R.layout.fragment_contact_list) {
    private var binding: FragmentContactListBinding? = null
    private var fragmentNavigator: FragmentNavigator? = null
    private var clickListener: ClickListener? = null
    private var viewModel: ContactListViewModel? = null
    private var contactListAdapter: ContactListAdapter? = null

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
        viewModel?.updateContactList()
        contactListAdapter = ContactListAdapter(fragmentNavigator, clickListener)
        binding?.let {
            it.rvContactList.layoutManager = LinearLayoutManager(context)
            it.rvContactList.adapter = contactListAdapter
            val divider = ContextCompat.getDrawable(view.context, R.drawable.item_decoration)
            divider?.let { itDivider ->
                it.rvContactList.addItemDecoration(ItemDecoration(itDivider))
            }
        }
        viewModel?.contactList?.observe(viewLifecycleOwner) {
            contactListAdapter?.submitList(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.search_action)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel?.updateContactList()
                val contactList = viewModel?.contactList
                val searchText = p0?.lowercase(Locale.getDefault()) ?: ""
                val newContactList = sortedSetOf<Contact>({ o1, o2 -> o1.id.compareTo(o2.id) })

                if (searchText.isNotEmpty()) {
                    contactList?.value?.forEach {
                        if (it.firstName.lowercase(Locale.getDefault()).contains(searchText) ||
                            it.lastName.lowercase(Locale.getDefault()).contains(searchText)
                        ) {
                            newContactList.add(it)
                        }
                    }
                    viewModel?.replaceContactListForSearch(newContactList)
                }

                return false
            }
        })
    }

    companion object {
        val FRAGMENT_CONTACT_LIST = "FRAGMENT_CONTACT_LIST"
        fun newInstance() = ContactListFragment()
    }
}