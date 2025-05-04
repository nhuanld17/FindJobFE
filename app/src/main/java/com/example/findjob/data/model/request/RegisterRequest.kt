package com.example.findjob.data.model.request

data class RegisterRequest(
    val role: String,
    val username: String,
    val email: String,
    val password: String
)