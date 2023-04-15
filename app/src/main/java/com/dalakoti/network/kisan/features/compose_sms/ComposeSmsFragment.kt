package com.dalakoti.network.kisan.features.compose_sms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dalakoti.network.core.data.models.Contact
import com.dalakoti.network.kisan.databinding.FragmentComposeSmsBinding
import com.dalakoti.network.kisan.utils.loadImageByUrl

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ComposeSmsFragment : Fragment() {

    private var _binding: FragmentComposeSmsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentComposeSmsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getSerializable("contact")?.let {
            (it as? Contact)?.let {contact->
                // set Data
                binding.appBar.title = contact.name
                binding.tvDesignation.text = contact.designation
                binding.icAvatar.loadImageByUrl(contact.avatar)
                binding.tvPhoneNumber.text = contact.phoneNumber
                binding.tvAddress.text = contact.address
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}