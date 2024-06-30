package com.example.bticapplication.feature.splash

import com.example.bticapplication.feature.authen.AuthRepository
import com.example.bticapplication.feature.authen.model.RefreshAccessRequest
import com.example.bticapplication.feature.authen.model.User
import com.example.bticapplication.feature.cinema.CinemaBrand
import com.example.bticapplication.feature.cinema.CinemaBrandLocalApi
import com.example.bticapplication.feature.cinema.CinemaBrandRemoteApi
import javax.inject.Inject

class SplashScreenRepositoryImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val cinemaBrandRemoteApi: CinemaBrandRemoteApi,
    private val cinemaBrandLocalApi: CinemaBrandLocalApi
) : SplashScreenRepository {
    override suspend fun refreshAccessToken(email: String, refreshToken: String): User {
        val response = authRepository.refreshAccessToken(RefreshAccessRequest(email, refreshToken))
        return response.user
    }

    override suspend fun getBrandCinemaList(): List<CinemaBrand> = cinemaBrandRemoteApi.getList()

    override suspend fun getMovieList() {
        TODO("Not yet implemented")
    }

    override suspend fun saveCinemaBrandList(cinemaBrandList: List<CinemaBrand>): Unit =
        cinemaBrandLocalApi.saveListCinema(cinemaBrandList)
}