package com.example.findjob.data.model

data class RegisterRequest(
    val role: String,
    val name: String,
    val email: String,
    val password: String
)