package com.demo.git5.domain.usecase

import com.demo.git5.domain.model.*
import com.demo.git5.utils.state.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Cuong Nguyen
 * UserUseCase
 */
interface UserUseCase {

    suspend fun getUsers(username : String) : Flow<ResultState<List<UserSearchItem>>>

    suspend fun getUserDetail(username : String) : Flow<ResultState<UserDetail>>

    suspend fun getUserRepos(username : String) : Flow<ResultState<List<UserRepo>>>
}
