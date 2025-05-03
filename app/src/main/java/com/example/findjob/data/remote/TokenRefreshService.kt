package com.example.findjob.data.remote

import com.example.findjob.data.model.AuthResponse
import javax.inject.Singleton

@Singleton
interface TokenRefreshService {
    suspend fun refreshToken(): Result<AuthResponse>
} 