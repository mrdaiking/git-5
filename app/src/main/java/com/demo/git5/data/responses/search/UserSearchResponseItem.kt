package com.demo.git5.data.responses.search


import com.google.gson.annotations.SerializedName

/**
 * User item in search user repsonse
 */
data class UserSearchResponseItem(
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("login")
    val login: String? = null,
)
