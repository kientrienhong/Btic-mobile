package com.example.bticapplication.feature.authen.model

import kotlinx.serialization.Serializable

@Serializable
class SignInRequest(val email: String, val password: String)