package com.example.findjob.data.model

data class AuthResponse(
    val username: String,
    val roles: String,
    val token: String,
    val refreshToken: String
)
