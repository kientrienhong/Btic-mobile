package com.example.bticapplication.path

object ServicePath {
    const val BASE_PATH = "http:192.168.1.29:9292/"

    object Auth {
        private const val BASE_PATH = "auth/"

        const val SIGN_IN = BASE_PATH + "login"
        const val SIGN_UP = BASE_PATH + "sign-up"
    }
}