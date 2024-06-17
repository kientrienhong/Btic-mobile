package com.example.bticapplication.feature.authen

import com.example.bticapplication.feature.authen.model.SignInResponse
import com.example.bticapplication.feature.authen.model.SignUpResponse
import com.example.bticapplication.path.ServicePath
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthRepository {
    @FormUrlEncoded
    @POST(ServicePath.Auth.SIGN_IN)
    suspend fun signIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<SignInResponse>

    @POST(ServicePath.Auth.SIGN_UP)
    suspend fun signUp(email: String, password: String): Response<SignUpResponse>
}