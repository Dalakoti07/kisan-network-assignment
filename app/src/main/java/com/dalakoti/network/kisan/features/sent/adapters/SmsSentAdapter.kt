package com.dalakoti.network.kisan.features.sent.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dalakoti.network.core.data.room.entities.SmsSentEntity
import com.dalakoti.network.kisan.databinding.ItemSmsSentBinding

class SmsSentAdapter : ListAdapter<SmsSentEntity, SmsSentViewHolder>(SmsSentEntity.DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmsSentViewHolder {
        return SmsSentViewHolder(
            ItemSmsSentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SmsSentViewHolder, position: Int) {
        holder.setData(getItem(position))
    }

}