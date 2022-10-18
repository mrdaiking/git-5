package com.demo.git5.data.source.remote.impl

import android.util.Log
import com.demo.git5.api.IGithubService
import com.demo.git5.data.source.remote.IUserDataRemote
import com.demo.git5.domain.model.UserDetail
import com.demo.git5.domain.model.UserRepo
import com.demo.git5.domain.model.UserSearchItem
import com.demo.git5.utils.Constants.SERVER_ERROR
import com.demo.git5.utils.DataMapper
import com.demo.git5.utils.state.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Implementation of UserDataRemote interface
 */
class UserDataRemoteImpl @Inject constructor(
    private val githubService: IGithubService
) : IUserDataRemote {

    private val TAG = "--USERDATA-REMOTE--"
    /**
     * Remote
     */

    /**
     * Get users by search keywords
     * @param username
     * @return Flow
     */
    override suspend fun getUsers(username: String): Flow<ResultState<List<UserSearchItem>>> {
        return flow {
            try {
                val response = githubService.getSearchUser(username)
                val userItems = response.userItems
                val dataMaped = userItems?.let { listSearchUser ->
                    DataMapper.mapUserSearchResponseToUserSearchItems(listSearchUser)
                }
                emit(ResultState.Success(dataMaped))
                Log.i(TAG, "Search users success!")
            } catch (e: Exception) {e
                emit(ResultState.Error(e.toString(), SERVER_ERROR))
                Log.i(TAG, "Search users failure!")
            }
        }.flowOn(Dispatchers.IO)
    }

    /**
     * Get users details by search username
     * @param username
     * @return Flow
     */
    override suspend fun getUserDetail(username: String): Flow<ResultState<UserDetail>> {
        return flow {
            try {
                val response = githubService.getUserDetail(username)
                val dataMaped = DataMapper.mapUserDetailResponseToUserDetail(response)
                emit(ResultState.Success(dataMaped))
                Log.i(TAG, "Search users success!")
            } catch (e: Exception) {
                emit(ResultState.Error(e.toString(), SERVER_ERROR))
                Log.i(TAG, "Search users failure!")
            }
        }.flowOn(Dispatchers.IO)
    }

    /**
     * Get user repositories by username
     * @param username
     * @return Flow
     */
    override suspend fun getUserRepos(username: String): Flow<ResultState<List<UserRepo>>> {
        return flow {
            try {
                val response = githubService.getUserRepos(username)
                val mapedData = DataMapper.mapUserRepoResponseToUserRepo(response.filter { it.fork == false}) // Only get unfork repositories
                emit(ResultState.Success(mapedData))
                Log.i(TAG, "Search users success!")
            } catch (e: Exception) {
                emit(ResultState.Error(e.toString(), SERVER_ERROR))
                Log.i(TAG, "Search users failure!")
            }
        }.flowOn(Dispatchers.IO)
    }
}