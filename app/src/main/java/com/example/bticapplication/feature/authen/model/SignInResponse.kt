package com.example.bticapplication.feature.authen.model

import kotlinx.serialization.Serializable

@Serializable
class SignInResponse(val user: User, val accessToken: String)