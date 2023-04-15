package com.dalakoti.network.kisan.features.contacts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.dalakoti.network.core.data.models.Contact
import com.dalakoti.network.kisan.R
import com.dalakoti.network.kisan.databinding.FragmentContactListBinding
import com.dalakoti.network.kisan.features.contacts.adapter.ContactsAdapter
import com.google.android.material.snackbar.Snackbar

class ContactListFragment : Fragment() {

    private var _binding: FragmentContactListBinding? = null

    private val binding get() = _binding!!

    private lateinit var rvAdapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        rvAdapter.submitList(
            listOf(
                Contact(
                    id = 1,
                    name = "Saurabh Dalakoti",
                    designation = "Software Engineer",
                    address = "Gurgaon, Haryana, India, Asia",
                    avatar = "https://avatars.githubusercontent.com/u/38468299?v=4",
                    phoneNumber = "9643957240"
                ),
                Contact(
                    id = 2,
                    name = "Harnoor Singh",
                    designation = "Software Engineer",
                    address = "Seattle, USA, America",
                    avatar = "https://avatars.githubusercontent.com/u/25940948?v=4",
                    phoneNumber = "9643957240"
                ),
                Contact(
                    id = 3,
                    name = "Harkirat Singh",
                    designation = "Remote Engineer",
                    address = "Earth",
                    avatar = "https://avatars.githubusercontent.com/u/8079861?v=4",
                    phoneNumber = "9643957240"
                ),
            )
        )
    }

}