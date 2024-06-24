package com.example.bticapplication.feature.authen

import com.example.bticapplication.feature.authen.model.RefreshAccessResponse
import com.example.bticapplication.feature.authen.model.SignInRequest
import com.example.bticapplication.feature.authen.model.SignInResponse
import com.example.bticapplication.feature.authen.model.SignUpResponse
import com.example.bticapplication.path.ServicePath
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthRemoteApi {
    @Headers("Content-Type:application/json")
    @POST(ServicePath.Auth.SIGN_IN)
    suspend fun signIn(@Body signInRequest: SignInRequest): Response<SignInResponse>

    @Headers("Content-Type:application/json")
    @POST(ServicePath.Auth.REFRESH_ACCESS_TOKEN)
    suspend fun refreshAccessToken(
        email: String,
        refreshToken: String
    ): Response<RefreshAccessResponse>

    @POST(ServicePath.Auth.SIGN_UP)
    suspend fun signUp(email: String, password: String): Response<SignUpResponse>
}