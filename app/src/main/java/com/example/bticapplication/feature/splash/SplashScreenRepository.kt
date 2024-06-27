package com.example.bticapplication.feature.splash

import com.example.bticapplication.feature.authen.model.User
import com.example.bticapplication.feature.cinema.CinemaBrand
import com.example.bticapplication.path.ServicePath
import retrofit2.http.GET
import retrofit2.http.Headers

interface SplashScreenRepository {
    suspend fun refreshAccessToken(email: String, refreshToken: String): User

    @Headers("Content-Type:application/json")
    @GET(ServicePath.CinemaBrand.BASE_PATH)
    suspend fun getBrandCinemaList(): List<CinemaBrand>

    suspend fun getMovieList()
}