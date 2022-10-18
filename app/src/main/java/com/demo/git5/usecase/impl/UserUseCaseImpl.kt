package com.demo.git5.usecase.impl

import com.demo.git5.domain.repository.IUserRepository
import com.demo.git5.domain.usecase.UserUseCase
import javax.inject.Inject

/**
 * Implement use case
 */
class UserUseCaseImpl @Inject constructor(
    private val userRepository: IUserRepository
) : UserUseCase {

    override suspend fun getUsers(username: String) =
        userRepository.getUsers(username)


    override suspend fun getUserDetail(username: String) =
        userRepository.getUserDetail(username)


    override suspend fun getUserRepos(username: String) =
        userRepository.getUserRepos(username)
}