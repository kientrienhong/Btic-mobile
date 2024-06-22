package com.example.bticapplication.feature.authen

import android.content.Context
import com.example.bticapplication.feature.authen.model.User

interface AuthLocalApi {
    fun getUserFromLocal(context: Context): User?

    fun insertUserToLocal(context: Context, user: User)
}