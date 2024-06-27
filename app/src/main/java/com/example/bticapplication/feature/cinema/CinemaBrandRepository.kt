package com.example.bticapplication.feature.cinema

import com.example.bticapplication.path.ServicePath
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface CinemaBrandRepository {
    @Headers("Content-Type:application/json")
    @GET(ServicePath.CinemaBrand.BASE_PATH)
    fun getList(): List<CinemaBrand>

    @Headers("Content-Type:application/json")
    @POST(ServicePath.CinemaBrand.BASE_PATH)
    fun create(cinemaBrand: CinemaBrand)

    fun delete(cinemaBrand: CinemaBrand)

    fun update(cinemaBrand: CinemaBrand)
}