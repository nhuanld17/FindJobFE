package com.example.findjob.data.remote

import com.example.findjob.data.model.AuthResponse
import com.example.findjob.data.model.LoginRequest
import com.example.findjob.data.model.RegisterRequest
import com.example.findjob.data.model.RestResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<RestResponse<AuthResponse>>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RestResponse<AuthResponse>>

    @POST("auth/refresh")
    suspend fun refreshToken(): Response<RestResponse<AuthResponse>>
}
