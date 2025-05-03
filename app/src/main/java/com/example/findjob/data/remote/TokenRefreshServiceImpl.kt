package com.example.findjob.data.remote

import com.example.findjob.data.model.AuthResponse
import com.example.findjob.data.model.RefreshTokenRequest
import com.example.findjob.utils.TokenManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRefreshServiceImpl @Inject constructor(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) : TokenRefreshService {
    
    override suspend fun refreshToken(): Result<AuthResponse> {
        return try {
            val refreshToken = tokenManager.getRefreshToken() ?: return Result.failure(Exception("No refresh token available"))
            val response = apiService.refreshToken(RefreshTokenRequest(refreshToken = refreshToken))
            if (response.isSuccessful) {
                val authResponse = response.body()!!
                tokenManager.saveTokens(
                    accessToken = authResponse.accessToken,
                    refreshToken = authResponse.refreshToken
                )
                Result.success(authResponse)
            } else {
                Result.failure(Exception("Failed to refresh token"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 