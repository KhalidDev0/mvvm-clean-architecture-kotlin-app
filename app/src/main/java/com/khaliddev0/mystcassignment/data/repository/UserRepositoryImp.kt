package com.khaliddev0.mystcassignment.data.repository

import com.khaliddev0.mystcassignment.data.model.UserDto
import com.khaliddev0.mystcassignment.data.remote.UserApi
import com.khaliddev0.mystcassignment.domain.user.repository.UserRepository
import com.khaliddev0.mystcassignment.presentation.common.Resource
import com.khaliddev0.mystcassignment.presentation.common.util.NetworkUtility
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val networkUtility: NetworkUtility,
    private val userApi: UserApi,
) : UserRepository {
    override fun getUsersList(): Flow<Resource<Response<UserDto>>> = flow {
        networkUtility.safeApiCall {
            userApi.getUserList()
        }.collect { results -> emit(results) }
    }
}