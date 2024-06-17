package com.example.bticapplication.feature.authen.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class SignUpResponse @OptIn(ExperimentalSerializationApi::class) constructor(
    @SerialName("user")
    val user: User,
    @SerialName("jwt")
    val accessToken: String
)