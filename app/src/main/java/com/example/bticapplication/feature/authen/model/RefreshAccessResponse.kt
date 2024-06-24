package com.example.bticapplication.feature.authen.model

import kotlinx.serialization.Serializable

@Serializable
data class RefreshAccessResponse(val user: User)
