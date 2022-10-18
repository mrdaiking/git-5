package com.demo.git5.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.git5.domain.model.UserSearchItem
import com.demo.git5.domain.usecase.UserUseCase
import com.demo.git5.utils.state.LoaderState
import com.demo.git5.utils.state.ResultState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Handle business logic to display on view
 */
class MainViewModel @ViewModelInject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    private val _error = MutableLiveData<String>()

    private val _networkError = MutableLiveData<Boolean>()
    val networkError: LiveData<Boolean>
        get() = _networkError

    private val _resultUserApi = MutableLiveData<List<UserSearchItem>>()
    val resultUserApi: LiveData<List<UserSearchItem>>
        get() = _resultUserApi

    /**
     * Search user
     * @param query
     */
    fun getUserFromApi(query: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            userUseCase.getUsers(query).collect {
                when (it) {
                    is ResultState.Success -> {
                        _resultUserApi.postValue(it.data)
                        _state.value = LoaderState.HideLoading
                    }
                    is ResultState.Error -> {
                        _error.postValue(it.error)
                    }
                    is ResultState.NetworkError -> {
                        _networkError.postValue(true)
                    }
                }
            }
        }
    }

}