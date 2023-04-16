package com.dalakoti.network.kisan.features.sent.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.dalakoti.network.core.data.room.entities.SmsSentEntity
import com.dalakoti.network.kisan.databinding.ItemSmsSentBinding

class SmsSentViewHolder (private val binding: ItemSmsSentBinding): RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun setData(smsSentEntity: SmsSentEntity){
        binding.tvInitials.text = (smsSentEntity.sendToName[0]+"").uppercase()
        binding.tvSId.text = smsSentEntity.body
        binding.tvMobile.text = smsSentEntity.mobileNumber
        binding.tvTime.text = smsSentEntity.dateAndTime
    }

}