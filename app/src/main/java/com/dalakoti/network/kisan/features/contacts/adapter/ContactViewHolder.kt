package com.dalakoti.network.kisan.features.contacts.adapter

import androidx.recyclerview.widget.RecyclerView
import com.dalakoti.network.core.data.models.Contact
import com.dalakoti.network.kisan.databinding.ItemContactBinding
import com.dalakoti.network.kisan.utils.loadImageByUrl

class ContactViewHolder(private val binding: ItemContactBinding): RecyclerView.ViewHolder(binding.root) {

    fun setItem(contact: Contact, onClicked: (Contact)-> Unit){
        binding.icAvatar.loadImageByUrl(
            url = contact.avatar,
        )
        binding.tvName.text = contact.name
        binding.tvDesignation.text = contact.designation
        binding.tvAddress.text = contact.address
        binding.root.setOnClickListener {
            onClicked.invoke(contact)
        }
    }

}