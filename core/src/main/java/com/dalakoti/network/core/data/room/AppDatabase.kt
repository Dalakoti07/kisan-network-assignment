package com.dalakoti.network.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dalakoti.network.core.data.room.dao.SmsSentDao
import com.dalakoti.network.core.data.room.entities.SmsSentEntity

@Database(entities = [SmsSentEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun smsSentDao(): SmsSentDao
}