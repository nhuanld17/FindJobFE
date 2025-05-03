package com.example.findjob.data.repository

import com.example.findjob.data.model.AuthResponse
import com.example.findjob.data.model.LoginRequest
import com.example.findjob.data.model.RegisterRequest
import com.example.findjob.data.model.RestResponse
import com.example.findjob.data.remote.api.AuthApi
import com.example.findjob.utils.TokenManager
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val tokenManager: TokenManager
) {
    suspend fun login(username: String, password: String): Result<AuthResponse> {
        return try {
            val response = api.login(LoginRequest(username, password))
            handleAuthResponse(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(role: String, username: String, email: String, password: String): Result<AuthResponse> {
        return try {
            val response = api.register(RegisterRequest(role, username, email, password))
            handleAuthResponse(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun handleAuthResponse(response: Response<RestResponse<AuthResponse>>): Result<AuthResponse> {
        return if (response.isSuccessful) {
            val authResponse = response.body()?.data
            if (authResponse != null) {
                tokenManager.saveTokens(accessToken = authResponse.accessToken)
                Result.success(authResponse)
            } else {
                Result.failure(Exception("Invalid response format"))
            }
        } else {
            Result.failure(Exception("Authentication failed: ${response.code()}"))
        }
    }
}