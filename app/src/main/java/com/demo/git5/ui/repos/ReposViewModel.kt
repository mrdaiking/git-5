package com.demo.git5.ui.repos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.git5.domain.model.UserRepo
import com.demo.git5.domain.usecase.UserUseCase
import com.demo.git5.utils.state.LoaderState
import com.demo.git5.utils.state.ResultState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ReposViewModel @ViewModelInject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    /**
     * Loader state
     */
    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    /**
     * Error state
     */
    private val _error = MutableLiveData<String>()

    /**
     * Network error
     */
    private val _networkError = MutableLiveData<Boolean>()

    /**
     * State Followers
     */
    private val _resultUserFollower = MutableLiveData<List<UserRepo>>()
    val resultUserFollower: LiveData<List<UserRepo>>
        get() = _resultUserFollower

    fun getUserFollowers(username: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            userUseCase.getUserRepos(username).collect {
                _state.value = LoaderState.HideLoading
                when (it) {
                    is ResultState.Success -> _resultUserFollower.postValue(it.data)
                    is ResultState.Error -> _error.postValue(it.error)
                    is ResultState.NetworkError -> _networkError.postValue(true)
                }
            }
        }
    }


}