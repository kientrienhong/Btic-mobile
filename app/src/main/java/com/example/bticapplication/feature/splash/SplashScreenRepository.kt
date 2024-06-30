package com.example.bticapplication.feature.splash

import com.example.bticapplication.feature.authen.model.User
import com.example.bticapplication.feature.cinema.CinemaBrand

interface SplashScreenRepository {
    suspend fun refreshAccessToken(email: String, refreshToken: String): User

    suspend fun getBrandCinemaList(): List<CinemaBrand>

    suspend fun getMovieList()

    suspend fun saveCinemaBrandList(cinemaBrandList: List<CinemaBrand>)
}