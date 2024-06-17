package com.example.bticapplication.feature.authen.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val email: String,
    val name: String,
    val birthDay: String,
    val refreshToken: String,
    val role: Role
)

enum class Role {
    Admin,
    User
}