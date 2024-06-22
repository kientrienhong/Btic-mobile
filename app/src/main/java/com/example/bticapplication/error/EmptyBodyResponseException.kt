package com.example.bticapplication.error

class EmptyBodyResponseException(override val message: String = "Empty body response") :
    RuntimeException(message)