package com.dalakoti.network.kisan.features.sent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dalakoti.network.kisan.R
import com.dalakoti.network.kisan.databinding.FragmentComposeSmsBinding
import com.dalakoti.network.kisan.databinding.FragmentSmsSentBinding
import com.dalakoti.network.kisan.features.sent.adapters.SmsSentAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private const val TAG = "SmsSentFragment"

@AndroidEntryPoint
class SmsSentFragment : Fragment() {

    private val viewModel: SmsSentViewModel by viewModels()

    private var _binding: FragmentSmsSentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: SmsSentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSmsSentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SmsSentAdapter()
        binding.rvSent.adapter = adapter

        viewModel.uiState.onEach {
            Log.d(TAG, "onViewCreated: size = ${it.list.size}")
            if(it.list.isEmpty()){
                binding.ivEmpty.isVisible = true
                binding.rvSent.isVisible = false
            }else{
                binding.ivEmpty.isVisible = false
                binding.rvSent.isVisible = true
                adapter.submitList(it.list)
            }
        }.launchIn(lifecycleScope)
    }

}