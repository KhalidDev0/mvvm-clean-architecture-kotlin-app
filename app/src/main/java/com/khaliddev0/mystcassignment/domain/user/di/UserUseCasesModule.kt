package com.khaliddev0.mystcassignment.domain.user.di

import android.content.Context
import com.khaliddev0.mystcassignment.domain.user.repository.UserRepository
import com.khaliddev0.mystcassignment.domain.user.usecase.GetUserListUseCase
import com.khaliddev0.mystcassignment.presentation.common.util.NetworkUtility
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserUseCasesModule {

    @Singleton
    @Provides
    fun provideGetUserListUseCase(
        @ApplicationContext applicationContext: Context,
        userRepository: UserRepository,
        networkUtility: NetworkUtility,
    ) =
        GetUserListUseCase(userRepository, networkUtility, applicationContext)
}