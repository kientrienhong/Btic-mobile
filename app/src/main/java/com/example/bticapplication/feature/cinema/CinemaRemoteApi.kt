package com.example.bticapplication.feature.cinema

import retrofit2.http.Body

interface CinemaRemoteApi {


    suspend fun create(@Body cinema: Cinema)
}