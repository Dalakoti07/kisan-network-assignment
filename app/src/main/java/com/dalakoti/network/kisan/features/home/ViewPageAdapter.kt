package com.dalakoti.network.kisan.features.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dalakoti.network.kisan.features.contacts.ContactListFragment
import com.dalakoti.network.kisan.features.sent.SmsSentFragment

class ViewPageAdapter( fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(
    fragmentManager, lifecycle
) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ContactListFragment()
            1 -> SmsSentFragment()
            else -> ContactListFragment()
        }
    }

}