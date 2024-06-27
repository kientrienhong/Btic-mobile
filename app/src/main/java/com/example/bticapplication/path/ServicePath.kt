package com.example.bticapplication.path

object ServicePath {
    const val BASE_PATH = "http://10.0.2.2:9292/"

    object Auth {
        private const val BASE_PATH = "auth/"

        const val SIGN_IN = BASE_PATH + "login"
        const val SIGN_UP = BASE_PATH + "sign-up"
        const val REFRESH_ACCESS_TOKEN = BASE_PATH + "refresh-token"
    }

    object CinemaBrand {
        const val BASE_PATH = "cinema-brand"
    }
}