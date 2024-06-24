package com.example.bticapplication.feature.authen

import android.content.Context
import com.example.bticapplication.PreferenceUtil
import com.example.bticapplication.feature.authen.model.User
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AuthLocalApiImpl @Inject constructor() : AuthLocalApi {
    override fun getUserFromLocal(context: Context): User? {
        val userString = PreferenceUtil.get(context, PreferenceUtil.PROFILE, isSecure = true)
        return if (userString == null) {
            null
        } else {
            Json.decodeFromString<User>(userString)
        }
    }

    override fun insertUserToLocal(context: Context, user: User) =
        PreferenceUtil.put(
            context,
            PreferenceUtil.PROFILE,
            Json.encodeToString(user),
            isSecure = true
        )

    override fun deleteUser(context: Context) =
        PreferenceUtil.remove(context, PreferenceUtil.PROFILE, isSecure = true)
}