package com.khaliddev0.mystcassignment.presentation.common

import com.khaliddev0.mystcassignment.data.model.ApiError

sealed class Resource<T : Any>{
    class Loading<T : Any> : Resource<T>()
    data class Success<T : Any>(val data: T) : Resource<T>()
    data class Error<T : Any>(val error: ApiError) : Resource<T>()
}