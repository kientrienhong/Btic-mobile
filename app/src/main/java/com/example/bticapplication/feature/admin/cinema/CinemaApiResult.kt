package com.example.bticapplication.feature.admin.cinema

sealed class CinemaApiResult<out T> {
    data class Success<T>(val data: T) : CinemaApiResult<T>()
    data class Error(val error: Exception) : CinemaApiResult<Nothing>()

    data object Loading : CinemaApiResult<Nothing>()
}