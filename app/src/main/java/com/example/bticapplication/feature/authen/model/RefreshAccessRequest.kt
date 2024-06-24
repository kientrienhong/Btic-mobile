package com.example.bticapplication.feature.authen.model

import kotlinx.serialization.Serializable

@Serializable
data class RefreshAccessRequest(val email: String, val refreshToken: String)