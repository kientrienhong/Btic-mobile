package com.example.bticapplication.feature.authen

import com.example.bticapplication.feature.authen.model.RefreshAccessRequest
import com.example.bticapplication.feature.authen.model.RefreshAccessResponse
import com.example.bticapplication.feature.authen.model.SignInRequest
import com.example.bticapplication.feature.authen.model.SignInResponse
import com.example.bticapplication.feature.authen.model.SignUpResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun signIn(signInRequest: SignInRequest): SignInResponse

    suspend fun signUp(email: String, password: String): Response<SignUpResponse>

    suspend fun refreshAccessToken(
        refreshAccessRequest: RefreshAccessRequest
    ): RefreshAccessResponse
}