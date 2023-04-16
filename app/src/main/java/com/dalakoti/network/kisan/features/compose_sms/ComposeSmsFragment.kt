package com.dalakoti.network.kisan.features.compose_sms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dalakoti.network.core.data.models.Contact
import com.dalakoti.network.core.domain.vm.UiEvents
import com.dalakoti.network.kisan.databinding.FragmentComposeSmsBinding
import com.dalakoti.network.kisan.utils.loadImageByUrl
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class ComposeSmsFragment : Fragment() {

    private val viewModel: ComposeSmsViewModel by viewModels()

    private var _binding: FragmentComposeSmsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var personContact: Contact

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
            (it as? Contact)?.let { contact ->
                // set Data
                personContact = contact
                binding.tvDesignation.text = contact.designation
                binding.icAvatar.loadImageByUrl(contact.avatar)
                binding.tvPhoneNumber.text = contact.phoneNumber
                binding.tvAddress.text = contact.address
            }
        }
        setupToolbar()
        binding.ivSend.setOnClickListener {
            if (::personContact.isInitialized) {
                viewModel.sendSms(
                    to = personContact.phoneNumber,
                    body = binding.etMessage.text.toString()
                )
            }
        }
        viewModel.uiState.onEach {
            binding.progressCircular.isVisible = it.isLoading
        }.launchIn(lifecycleScope)

        viewModel.events.onEach {
            when(it){
                is UiEvents.ShowSnackBar->{
                    Snackbar.make(binding.root, it.string, Snackbar.LENGTH_SHORT).show()
                }
                else ->{}
            }
        }.launchIn(
            lifecycleScope
        )
    }

    private fun setupToolbar() {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        if(!::personContact.isInitialized)
            return
        binding.tvTitle.text = personContact.name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}