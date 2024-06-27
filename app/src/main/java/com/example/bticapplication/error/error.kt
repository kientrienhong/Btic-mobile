package com.example.bticapplication.error

class EmptyBodyResponseException(override val message: String = "Empty body response") :
    RuntimeException(message)

class UnAuthorizedException(override val message: String = "Unauthorized") :
    RuntimeException(message)