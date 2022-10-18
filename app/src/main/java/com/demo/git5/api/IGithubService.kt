package com.demo.git5.api

import com.demo.git5.data.responses.search.SearchUserResponse
import com.demo.git5.data.responses.detail.UserDetailResponse
import com.demo.git5.data.responses.repo.UserReposResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Definition of Github API endpoint
 * @see {@link https://docs.github.com/en/rest}
 */
interface IGithubService {

    /**
     * Endpoints search User
     * @param query: search query
     * @return SearchUserResponse object
     */
    @Headers("Content-Type: application/json")
    @GET("search/users?")
    suspend fun getSearchUser(
        @Query("q") q : String
    ) : SearchUserResponse

    /**
     * Endpoints Detail User
     * @param username
     * @return UserDetailResponse
     */
    @Headers("Content-Type: application/json")
    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") username: String
    ) : UserDetailResponse

    /**
     * Endpoints repositories of owner
     * @param username
     * @return UserReposResponse
     */
    @Headers("Content-Type: application/json")
    @GET("users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") username: String
    ) : UserReposResponse
}
