package com.demo.git5.data.responses.search


import com.google.gson.annotations.SerializedName

/**
 * Response of search user
 */
data class SearchUserResponse(
    @SerializedName("total_count")
    val totalCount: Int? = 0,
    @SerializedName("items")
    val userItems: List<UserSearchResponseItem>? = null
)
