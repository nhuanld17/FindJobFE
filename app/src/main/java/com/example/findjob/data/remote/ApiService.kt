package com.example.findjob.data.remote

import com.example.findjob.data.model.AuthResponse
import com.example.findjob.data.model.LoginRequest
import com.example.findjob.data.model.RefreshTokenRequest
import com.example.findjob.data.model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    @POST("auth/refresh")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): Response<AuthResponse>
} 