package com.khaliddev0.mystcassignment.presentation.common.util

import android.content.Context
import android.net.ConnectivityManager
import com.khaliddev0.mystcassignment.R
import com.khaliddev0.mystcassignment.data.model.ApiError
import com.khaliddev0.mystcassignment.presentation.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkUtility @Inject constructor(
    private val applicationContext: Context
) {
    fun isOnline(): Boolean {
        val connectivityManager: ConnectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) != null
    }

    fun <T : Any> safeApiCall(
        apiToBeCalled: suspend () -> T,
    ): Flow<Resource<T>> = flow {
        try {
            emit(Resource.Loading())

            val response = apiToBeCalled.invoke()
            emit(Resource.Success(response))

        } catch (ex: Exception) {
            emit(
                Resource.Error(
                    ApiError(
                        NetworkError.UNKNOWN_ERROR.code, applicationContext.getString(
                            R.string.msg_unknown_error
                        )
                    )
                )
            )
        }
    }

    enum class NetworkError(val code: Int) {
        NO_INTERNET(1000),
        UNKNOWN_ERROR(3000),
    }
}