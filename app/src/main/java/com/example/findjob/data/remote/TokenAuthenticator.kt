package com.example.findjob.data.remote

import com.example.findjob.utils.TokenManager
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val authApi: AuthApi
) : Authenticator {
    private var retryCount = 0
    private val maxRetries = 3

    override suspend fun authenticate(route: Route?, response: Response): Request? {
        if (retryCount >= maxRetries) {
            return null // Không thử refresh token nữa nếu đã thử quá số lần cho phép
        }
        retryCount++

        try {
            val refreshToken = tokenManager.refreshToken
            if (refreshToken.isNullOrEmpty()) {
                return null // Không có refresh token để thử
            }

            val newTokens = refreshToken()
            if (newTokens != null) {
                tokenManager.accessToken = newTokens.accessToken
                tokenManager.refreshToken = newTokens.refreshToken
                retryCount = 0 // Reset retry count khi refresh thành công
                
                return response.request.newBuilder()
                    .header("Authorization", "Bearer ${newTokens.accessToken}")
                    .build()
            }
        } catch (e: IOException) {
            // Xử lý lỗi khi refresh token thất bại
            e.printStackTrace()
        }
        
        return null
    }

    private suspend fun refreshToken(): AuthResponse? {
        return try {
            val response = authApi.refreshToken()
            if (response.isSuccessful) {
                response.body()?.data
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}