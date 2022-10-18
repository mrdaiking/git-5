package com.demo.git5.domain.model

/**
 * Created by Cuong Nguyen
 * UserDetail
 */
data class UserDetail (
    val username: String = "",
    val name: String? = "",
    val avatarUrl: String? = null,
    val followingUrl: String? = null,
    val bio: String? = "",
    val company: String? = "",
    val publicRepos: Int? = null,
    val followersUrl: String? = null,
    val followers: Int? = null,
    val following: Int? = null,
    val location: String? = null
)
