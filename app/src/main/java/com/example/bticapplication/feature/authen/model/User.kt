package com.example.bticapplication.feature.authen.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("name")
    val name: String,
    @SerialName("birthDay")
    val birthDay: String,
    @SerialName("role")
    val role: String,
    @SerialName("refreshToken")
    val refreshToken: String
)

enum class Role {
    Admin,
    User
}