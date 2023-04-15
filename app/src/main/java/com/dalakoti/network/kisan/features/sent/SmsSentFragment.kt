package com.dalakoti.network.kisan.features.sent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dalakoti.network.kisan.R

class SmsSentFragment : Fragment() {

    companion object {
        fun newInstance() = SmsSentFragment()
    }

    private lateinit var viewModel: SmsSentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sms_sent, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SmsSentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}