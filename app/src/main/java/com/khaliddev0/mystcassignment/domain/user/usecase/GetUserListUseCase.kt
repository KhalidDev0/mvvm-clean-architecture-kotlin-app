package com.khaliddev0.mystcassignment.domain.user.usecase

import android.content.Context
import com.khaliddev0.mystcassignment.R
import com.khaliddev0.mystcassignment.data.model.ApiError
import com.khaliddev0.mystcassignment.data.model.UserDto
import com.khaliddev0.mystcassignment.data.model.toUser
import com.khaliddev0.mystcassignment.domain.user.model.User
import com.khaliddev0.mystcassignment.domain.user.repository.UserRepository
import com.khaliddev0.mystcassignment.presentation.common.Resource
import com.khaliddev0.mystcassignment.presentation.common.util.NetworkUtility
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val networkUtility: NetworkUtility,
    private val applicationContext: Context,
) {
    operator fun invoke(): Flow<Resource<List<User>>> = flow {
        if (!networkUtility.isOnline()) {
            emit(
                Resource.Error(
                    ApiError(
                        NetworkUtility.NetworkError.NO_INTERNET.code,
                        applicationContext.getString(R.string.msg_no_internet)
                    )
                )
            )
            return@flow
        }

        userRepository.getUsersList().collect { result ->
            when (result) {
                is Resource.Loading -> {
                    emit(Resource.Loading())
                }
                is Resource.Success -> {
                    emit(Resource.Success(toUserList(result.data.body()!!)))
                }
                is Resource.Error -> {
                    emit(Resource.Error(result.error))
                }
            }
        }
    }

    private fun toUserList(userDto: UserDto): List<User> = userDto.UserDataItem.map { it.toUser() }
}