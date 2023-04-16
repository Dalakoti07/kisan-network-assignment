package com.dalakoti.network.core.data.room.entities

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sms_sent")
data class SmsSentEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int? = null,

    @ColumnInfo(name = "sid")
    val sid: String,

    @ColumnInfo(name = "mobile_number")
    val mobileNumber: String,

    @ColumnInfo(name = "send_to_name")
    val sendToName: String,

    val uri: String,

    val body: String = "",

    @ColumnInfo(name = "date_and_time")
    val dateAndTime: String = ""
){

    class DiffUtilCallBack : DiffUtil.ItemCallback<SmsSentEntity>() {
        override fun areItemsTheSame(
            oldItem: SmsSentEntity,
            newItem: SmsSentEntity
        ): Boolean = oldItem.uid == newItem.uid

        override fun areContentsTheSame(
            oldItem: SmsSentEntity,
            newItem: SmsSentEntity
        ): Boolean = oldItem == newItem
    }

}
