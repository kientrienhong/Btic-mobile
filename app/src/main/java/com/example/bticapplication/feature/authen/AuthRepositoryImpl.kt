package com.example.bticapplication.feature.authen

import android.content.Context
import com.example.bticapplication.error.EmptyBodyResponseException
import com.example.bticapplication.extensions.runBlocking
import com.example.bticapplication.feature.authen.model.RefreshAccessRequest
import com.example.bticapplication.feature.authen.model.RefreshAccessResponse
import com.example.bticapplication.feature.authen.model.SignInRequest
import com.example.bticapplication.feature.authen.model.SignInResponse
import com.example.bticapplication.feature.authen.model.SignUpResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authRemoteApi: AuthRemoteApi,
    private val authLocalApi: AuthLocalApi,
) : AuthRepository {

    override suspend fun signIn(signInRequest: SignInRequest): SignInResponse {
        val localUser = authLocalApi.getUserFromLocal(context)
        if (localUser != null) {
            return SignInResponse(localUser, localUser.accessToken)
        }
        val response = authRemoteApi.signIn(signInRequest)
        return response.runBlocking(
            onSuccess = {
                val user = it.user.copy(accessToken = it.jwt)
                authLocalApi.insertUserToLocal(context, user)
                SignInResponse(user, it.jwt)
            },
            onThrowsException = {
                val message = response.errorBody().toString()
                throw EmptyBodyResponseException(message)
            }
        )
    }

    override suspend fun signUp(email: String, password: String): Response<SignUpResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshAccessToken(
        refreshAccessRequest: RefreshAccessRequest
    ): RefreshAccessResponse {
        val response = authRemoteApi.refreshAccessToken(
            email = refreshAccessRequest.email,
            refreshToken = refreshAccessRequest.refreshToken
        )

        return response.runBlocking(
            onSuccess = { it },
            onThrowsException = {
                val message = response.errorBody().toString()
                throw EmptyBodyResponseException(message)
            }
        )
    }
}