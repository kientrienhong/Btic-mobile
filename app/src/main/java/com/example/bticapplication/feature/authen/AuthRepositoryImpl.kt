package com.example.bticapplication.feature.authen

import android.content.Context
import com.example.bticapplication.error.EmptyBodyResponseException
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
        val signInResponse = response.body()
        if (response.isSuccessful && signInResponse != null) {
            val user = signInResponse.user.copy(accessToken = signInResponse.jwt)
            authLocalApi.insertUserToLocal(context, user)
            return checkNotNull(response.body())
        } else {
            val message = response.errorBody().toString()
            throw EmptyBodyResponseException(message)
        }
    }

    override suspend fun signUp(email: String, password: String): Response<SignUpResponse> {
        TODO("Not yet implemented")
    }

    private companion object
}