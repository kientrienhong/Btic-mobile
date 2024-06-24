package com.example.bticapplication.feature.cinema

import kotlinx.serialization.Serializable

@Serializable
data class CinemaBrand(
    val id: String,
    val name: String,
    val imageUrl: String
)
