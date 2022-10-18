package com.demo.git5.repository.impl

import com.demo.git5.data.source.remote.IUserDataRemote
import com.demo.git5.domain.model.*
import com.demo.git5.domain.repository.IUserRepository
import com.demo.git5.utils.state.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * UserRepository implementation
 */
class IUserRepositoryImpl @Inject constructor(
    private val userDataRemote: IUserDataRemote
) : IUserRepository {

    /**
     * Remote
     */

    /**
     * Get users by search keywords
     */
    override suspend fun getUsers(username: String): Flow<ResultState<List<UserSearchItem>>> = userDataRemote.getUsers(username)
    /**
     * Get users details by search username
     */
    override suspend fun getUserDetail(username: String): Flow<ResultState<UserDetail>> = userDataRemote.getUserDetail(username)

    /**
     * Get user repositories by username
     */
    override suspend fun getUserRepos(username: String): Flow<ResultState<List<UserRepo>>> = userDataRemote.getUserRepos(username)
}
