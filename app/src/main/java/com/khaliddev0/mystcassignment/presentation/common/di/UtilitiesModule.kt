package com.khaliddev0.mystcassignment.presentation.common.di

import android.content.Context
import com.khaliddev0.mystcassignment.presentation.common.util.NetworkUtility
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilitiesModule {

    @Singleton
    @Provides
    fun provideNetworkUtility(
        @ApplicationContext applicationContext: Context,
    ) = NetworkUtility(applicationContext)
}