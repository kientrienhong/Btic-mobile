package com.example.bticapplication.feature.cinema

interface CinemaBrandRepository {
    fun getList(): List<CinemaBrand>

    fun create(cinemaBrand: CinemaBrand)

    fun delete(cinemaBrand: CinemaBrand)

    fun update(cinemaBrand: CinemaBrand)
}