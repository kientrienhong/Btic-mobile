package com.example.bticapplication.feature.authen.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    @SerialName("user")
    val user: User,
    @SerialName("jwt")
    val accessToken: String
)