package com.example.bticapplication.feature.cinema

import com.example.bticapplication.path.ServicePath
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface CinemaBrandRemoteApi {
    @Headers("Content-Type:application/json")
    @GET(ServicePath.CinemaBrand.BASE_PATH)
    suspend fun getList(): List<CinemaBrand>

    @Headers("Content-Type:application/json")
    @POST(ServicePath.CinemaBrand.BASE_PATH)
    suspend fun create(@Body cinemaBrand: CinemaBrand): CinemaBrand

    suspend fun delete(cinemaBrand: CinemaBrand)

    suspend fun update(cinemaBrand: CinemaBrand)
}