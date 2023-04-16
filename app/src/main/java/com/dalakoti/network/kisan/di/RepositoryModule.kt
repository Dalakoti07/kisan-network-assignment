package com.dalakoti.network.kisan.di

import com.dalakoti.network.core.data.rep_impl.TwilioRepositoryImpl
import com.dalakoti.network.core.data.repository.TwilioRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindsTwilioRepository(
        twilioRepository: TwilioRepositoryImpl
    ): TwilioRepository

}

