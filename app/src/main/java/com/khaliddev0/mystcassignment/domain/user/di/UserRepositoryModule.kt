package com.khaliddev0.mystcassignment.domain.user.di

import com.khaliddev0.mystcassignment.data.remote.UserApi
import com.khaliddev0.mystcassignment.data.repository.UserRepositoryImp
import com.khaliddev0.mystcassignment.domain.user.repository.UserRepository
import com.khaliddev0.mystcassignment.presentation.common.util.NetworkUtility
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {

    @Singleton
    @Provides
    fun provideGetUserListUseCase(
        networkUtility: NetworkUtility,
        userApi: UserApi,
    ): UserRepository = UserRepositoryImp(networkUtility, userApi)
}