package com.example.bticapplication.feature.authen.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
    val birthDay: String,
    val role: Role,
    val refreshToken: String,
    val accessToken: String = ""
)

enum class Role {
    Admin,
    User
}