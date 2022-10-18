package com.demo.git5.domain.model

/**
 * Created by Cuong Nguyen
 * Repository of user
 */
data class UserRepo(
    val id: Int? = null,
    val name: String? = null,
    val fork: Boolean? = null,
    val url: String?,
    val description: String? = null,
    val language: String? = null,
    val star: Int? = null,
)
