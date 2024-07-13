package com.example.bticapplication.feature.cinema

interface CinemaRepository {
    suspend fun create(cinemaBrandId: Int, cinema: Cinema)

    suspend fun getList(cinemaBrandId: Int): List<Cinema>

    suspend fun delete(cinemaBrandId: Int, cinema: Cinema)
}