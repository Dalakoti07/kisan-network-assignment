package com.dalakoti.network.kisan.features.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dalakoti.network.core.data.models.Contact
import com.dalakoti.network.kisan.databinding.ItemContactBinding

class ContactsAdapter (private val onItemClick: (Contact)-> Unit): ListAdapter<Contact, ContactViewHolder>(Contact.DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            binding = ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.setItem(
            contact = getItem(position),
            onClicked = {
                onItemClick.invoke(it)
            }
        )
    }

}