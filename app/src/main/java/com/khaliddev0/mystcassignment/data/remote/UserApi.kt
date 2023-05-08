package com.khaliddev0.mystcassignment.data.remote

import com.khaliddev0.mystcassignment.data.model.UserDto
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUserList(): Response<UserDto>
}