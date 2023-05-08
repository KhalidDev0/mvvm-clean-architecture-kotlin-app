package com.khaliddev0.mystcassignment.presentation.home.viewModel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaliddev0.mystcassignment.domain.user.usecase.GetUserListUseCase
import com.khaliddev0.mystcassignment.presentation.common.Resource
import com.khaliddev0.mystcassignment.presentation.home.view.adapter.HomeUserListAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    private val _userListAdapter = MutableSharedFlow<HomeUserListAdapter>()
    val userListAdapter = _userListAdapter.asSharedFlow()

    private val _errorMassage = MutableStateFlow<String?>(null)
    val errorMassage = _errorMassage.asSharedFlow()

    private val _loadingIndicatorVisibility = MutableStateFlow(View.VISIBLE)
    val loadingIndicatorVisibility = _loadingIndicatorVisibility.asStateFlow()

    init {
        getUserList()
    }

    fun getUserList() {
        getUserListUseCase().onEach { results ->
            when (results) {
                is Resource.Loading -> {
                    _loadingIndicatorVisibility.emit(View.VISIBLE)
                    _errorMassage.emit(null)
                }
                is Resource.Success -> {
                    _userListAdapter.emit(HomeUserListAdapter(results.data))
                    _loadingIndicatorVisibility.emit(View.GONE)
                }
                is Resource.Error -> {
                    _errorMassage.emit(results.error.message)
                    _loadingIndicatorVisibility.emit(View.GONE)
                }
            }
        }.launchIn(viewModelScope)
    }
}