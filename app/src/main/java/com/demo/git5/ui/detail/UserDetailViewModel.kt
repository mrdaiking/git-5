package com.demo.git5.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.demo.git5.domain.model.UserDetail
import com.demo.git5.domain.usecase.UserUseCase
import com.demo.git5.utils.state.LoaderState
import com.demo.git5.utils.state.ResultState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserDetailViewModel @ViewModelInject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    /**
     * Loader state
     */
    private val _state = MutableLiveData<LoaderState>()
    val state : LiveData<LoaderState>
        get() = _state

    /**
     * error
     */
    private val _error = MutableLiveData<String>()

    /**
     * Network error
     */
    private val _networkError = MutableLiveData<Boolean>()

    /**
     * User detail remote
     */
    private val _resultUserDetail = MutableLiveData<UserDetail>()
    val resultUserDetail : LiveData<UserDetail>
        get() = _resultUserDetail

    /**
     * Get user detail
     * @param username
     */
    fun getUserDetail(username: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            userUseCase.getUserDetail(username).collect {
                _state.value = LoaderState.HideLoading
                when(it) {
                    is ResultState.Success -> _resultUserDetail.postValue(it.data)
                    is ResultState.Error -> _error.postValue(it.error)
                    is ResultState.NetworkError -> _networkError.postValue(true)
                }
            }

        }
    }
}