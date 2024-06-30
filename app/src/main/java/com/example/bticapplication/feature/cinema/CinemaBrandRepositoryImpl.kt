package com.example.bticapplication.feature.cinema

import javax.inject.Inject

class CinemaBrandRepositoryImpl @Inject constructor(
    private val cinemaBrandLocalApi: CinemaBrandLocalApi,
    private val cinemaBrandRemoteApi: CinemaBrandRemoteApi
) : CinemaBrandRepository {
    override suspend fun getList(): List<CinemaBrand> = cinemaBrandLocalApi.getList()

    override suspend fun create(cinemaBrand: CinemaBrand) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(cinemaBrand: CinemaBrand) {
        TODO("Not yet implemented")
    }

    override suspend fun update(cinemaBrand: CinemaBrand) {
        TODO("Not yet implemented")
    }
}