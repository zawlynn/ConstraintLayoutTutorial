package com.zawlynn.appsynthcodechallenge.data.database.dao.model

data class UserInfo(
    val userId: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val followers: Long,
    val following: Long,
    val likes: Long
)