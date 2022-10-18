package com.demo.git5.data.responses.repo


import com.google.gson.annotations.SerializedName

/**
 * User Repository
 */
data class UserRepoItem(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("fork")
    val fork: Boolean? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("stargazers_count")
    val star: Int? = null,
)
