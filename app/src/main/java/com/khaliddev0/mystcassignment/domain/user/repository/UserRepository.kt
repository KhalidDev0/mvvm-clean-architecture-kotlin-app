package com.khaliddev0.mystcassignment.domain.user.repository

import com.khaliddev0.mystcassignment.data.model.UserDto
import com.khaliddev0.mystcassignment.presentation.common.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UserRepository {
    fun getUsersList(): Flow<Resource<Response<UserDto>>>
}