package com.example.bticapplication.feature.cinema

import com.example.bticapplication.path.ServicePath
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path


interface CinemaBrandRemoteApi {
    @Headers("Content-Type:application/json")
    @GET(ServicePath.CinemaBrand.BASE_PATH)
    suspend fun getList(): List<CinemaBrand>

    @Headers("Content-Type:application/json")
    @POST(ServicePath.CinemaBrand.BASE_PATH)
    suspend fun create(@Body cinemaBrand: CinemaBrand): CinemaBrand

    @Headers("Content-Type:application/json")
    @DELETE(ServicePath.CinemaBrand.BASE_PATH + "/{id}")
    suspend fun delete(@Path("id") id: Int)

    suspend fun update(cinemaBrand: CinemaBrand)
}