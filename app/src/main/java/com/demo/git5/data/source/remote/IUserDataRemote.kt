package com.demo.git5.data.source.remote

import com.demo.git5.domain.model.UserDetail
import com.demo.git5.domain.model.UserRepo
import com.demo.git5.domain.model.UserSearchItem
import com.demo.git5.utils.state.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * User data remote interface
 */
interface IUserDataRemote {

    /**
     * Remote
     */
    suspend fun getUsers(username: String) : Flow<ResultState<List<UserSearchItem>>>

    suspend fun getUserDetail(username: String) : Flow<ResultState<UserDetail>>

    suspend fun getUserRepos(username: String) : Flow<ResultState<List<UserRepo>>>
}