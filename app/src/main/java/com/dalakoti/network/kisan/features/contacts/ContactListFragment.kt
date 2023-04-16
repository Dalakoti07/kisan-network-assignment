package com.dalakoti.network.kisan.features.contacts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dalakoti.network.core.data.models.Contact
import com.dalakoti.network.kisan.R
import com.dalakoti.network.kisan.databinding.FragmentContactListBinding
import com.dalakoti.network.kisan.features.contacts.adapter.ContactsAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ContactListFragment : Fragment() {

    private var _binding: FragmentContactListBinding? = null

    private val binding get() = _binding!!

    private lateinit var rvAdapter: ContactsAdapter

    private val viewModel: ContactListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvAdapter = ContactsAdapter {
            // intent to go to next screen
            val bundle = bundleOf("contact" to it)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, args = bundle)
        }
        binding.rvContacts.adapter = rvAdapter
        viewModel.contactList.onEach {
            rvAdapter.submitList(
                it
            )
        }.launchIn(lifecycleScope)
    }

}