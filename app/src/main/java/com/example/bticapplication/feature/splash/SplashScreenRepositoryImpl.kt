package com.example.bticapplication.feature.splash

import com.example.bticapplication.feature.authen.AuthRepository
import com.example.bticapplication.feature.authen.model.RefreshAccessRequest
import com.example.bticapplication.feature.authen.model.User
import com.example.bticapplication.feature.cinema.CinemaBrand
import com.example.bticapplication.feature.cinema.CinemaBrandRepository
import javax.inject.Inject

class SplashScreenRepositoryImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val cinemaBrandRepository: CinemaBrandRepository,
) : SplashScreenRepository {
    override suspend fun refreshAccessToken(email: String, refreshToken: String): User {
        val response = authRepository.refreshAccessToken(RefreshAccessRequest(email, refreshToken))
        return response.user
    }

    override suspend fun getBrandCinemaList(): List<CinemaBrand> = cinemaBrandRepository.getList()

    override suspend fun getMovieList() {
        TODO("Not yet implemented")
    }
}