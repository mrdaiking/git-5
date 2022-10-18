package com.demo.git5.utils

import com.demo.git5.data.responses.repo.UserRepoItem
import com.demo.git5.data.responses.detail.UserDetailResponse
import com.demo.git5.data.responses.search.UserSearchResponseItem
import com.demo.git5.domain.model.*

/**
 * Created by Cuong Nguyen
 * DataMapper
 * In order to enhance security the response should be mapped to app Object
 */
object DataMapper {
    /**
     * Mapping UserSearchResponse -> List<UserSearchResponseItem>
     * @param List<UserSearchResponseItem>
     * @return List<UserSearchItem>
     */
    fun mapUserSearchResponseToUserSearchItems(data: List<UserSearchResponseItem>): List<UserSearchItem> =
        data.map {
            UserSearchItem(
                avatarUrl = it.avatarUrl,
                id = it.id,
                login = it.login,
            )
        }

    /**
     * Mapping UserDetailResponse -> UserDetail
     * @param UserDetailResponse
     * @return UserDetail
     */
    fun mapUserDetailResponseToUserDetail(data: UserDetailResponse): UserDetail =
        UserDetail(
            username = data.login.toString(),
            name = data.name,
            avatarUrl = data.avatarUrl,
            followersUrl = data.followersUrl,
            bio = data.bio,
            company = data.company,
            publicRepos = data.publicRepos,
            followingUrl = data.followingUrl,
            followers = data.followers,
            following = data.following,
            location = data.location
        )

    /**
     * Mapping UserRepoResponse -> List<UserRepo>
     * @param List<UserRepoItem>
     * @return List<UserRepo>
     */
    fun mapUserRepoResponseToUserRepo(data: List<UserRepoItem>): List<UserRepo> =
        data.map {
                UserRepo(
                    id = it.id,
                    name = it.name,
                    fork = it.fork,
                    url = it.url,
                    description = it.description,
                    language = it.language,
                    star = it.star?:0
                )
        }
}