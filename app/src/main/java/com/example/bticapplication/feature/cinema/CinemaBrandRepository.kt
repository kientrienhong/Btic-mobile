package com.example.bticapplication.feature.cinema

interface CinemaBrandRepository {
    suspend fun getList(): List<CinemaBrand>

    suspend fun create(cinemaBrand: CinemaBrand)

    suspend fun delete(cinemaBrand: CinemaBrand)

    suspend fun update(cinemaBrand: CinemaBrand)
}