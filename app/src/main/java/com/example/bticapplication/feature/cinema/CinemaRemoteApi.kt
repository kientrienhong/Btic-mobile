package com.example.bticapplication.feature.cinema

import com.example.bticapplication.path.ServicePath
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CinemaRemoteApi {

    suspend fun create(@Body cinema: Cinema): Boolean

    @Headers("Content-Type:application/json")
    @GET("${ServicePath.CinemaBrand.BASE_PATH}/{cinema_brand_id}/cinema")
    suspend fun getListCinema(
        @Path("cinema_brand_id", encoded = true) cinemaBrandId: Int
    ): List<Cinema>
}