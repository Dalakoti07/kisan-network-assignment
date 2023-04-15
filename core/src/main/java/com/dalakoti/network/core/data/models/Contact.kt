package com.dalakoti.network.core.data.models

import androidx.recyclerview.widget.DiffUtil

data class Contact(
    val id: Int,
    val name: String,
    val designation: String,
    val address: String,
    val avatar: String,
    val phoneNumber: String
): java.io.Serializable{
    class DiffUtilCallBack : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(
            oldItem: Contact,
            newItem: Contact
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Contact,
            newItem: Contact
        ): Boolean = oldItem == newItem
    }
}
