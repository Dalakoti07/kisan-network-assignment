package com.dalakoti.network.kisan.di

import android.content.Context
import androidx.room.Room
import com.dalakoti.network.core.data.room.AppDatabase
import com.dalakoti.network.core.data.room.dao.SmsSentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "kisan_network_db")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesSmsDao(appDatabase: AppDatabase): SmsSentDao {
        return appDatabase.smsSentDao()
    }

}