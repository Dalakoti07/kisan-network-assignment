package com.dalakoti.network.core.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dalakoti.network.core.data.room.entities.SmsSentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SmsSentDao {

    @Query("SELECT * FROM sms_sent")
    fun getAllSentSms(): Flow<List<SmsSentEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIntoDb(sms: SmsSentEntity)

}
