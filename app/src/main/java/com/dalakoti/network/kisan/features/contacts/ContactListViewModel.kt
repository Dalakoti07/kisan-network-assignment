package com.dalakoti.network.kisan.features.contacts

import androidx.lifecycle.ViewModel
import com.dalakoti.network.core.data.models.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor() : ViewModel() {

    private val _contactList = MutableStateFlow(mutableListOf<Contact>())
    val contactList: StateFlow<List<Contact>>
        get() = _contactList

    init {
        _contactList.value = mutableListOf(
            Contact(
                id = 4,
                name = "Kisan Network",
                designation = "Startup",
                address = "India",
                avatar = "https://i.imgur.com/uTSXYZa.png",
                phoneNumber = "+919810153260"
            ),
            Contact(
                id = 1,
                name = "Saurabh Dalakoti",
                designation = "Software Engineer",
                address = "Gurgaon, Haryana, India, Asia",
                avatar = "https://avatars.githubusercontent.com/u/38468299?v=4",
                phoneNumber = "+919643957240"
            ),
            Contact(
                id = 2,
                name = "Harnoor Singh",
                designation = "Software Engineer",
                address = "Seattle, USA, America",
                avatar = "https://avatars.githubusercontent.com/u/25940948?v=4",
                phoneNumber = "+919643957240"
            ),
            Contact(
                id = 3,
                name = "Harkirat Singh",
                designation = "Remote Engineer",
                address = "Earth",
                avatar = "https://avatars.githubusercontent.com/u/8079861?v=4",
                phoneNumber = "+919643957240"
            )
        )
    }

}