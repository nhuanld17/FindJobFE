package com.example.findjob.data.repository

import com.example.findjob.data.model.request.RecruiterRequest
import com.example.findjob.data.model.response.RecruiterResponse
import com.example.findjob.data.remote.api.RecruiterApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecruiterRepository @Inject constructor(
    private val recruiterApi: RecruiterApi
) {
    suspend fun updateRecruiterProfile(
        recruiterRequest: RecruiterRequest
    ): Result<Unit> {
        return try {
            val response = recruiterApi.updateRecruiterProfile(recruiterRequest)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody?.statusCode in 200..299) {
                    Result.success(Unit)
                } else {
                    Result.failure(Exception(responseBody?.message ?: "Failed to update recruiter profile"))
                }
            } else {
                Result.failure(Exception("Failed to update recruiter profile"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getRecruiterProfile(): Result<RecruiterResponse> {
        return try {
            val response = recruiterApi.getRecruiterProfile()
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody?.statusCode in 200..299) {
                    Result.success(responseBody?.data ?: RecruiterResponse(
                        about = null,
                        website = null,
                        industry = null,
                        location = null,
                        since = null,
                        specialization = null
                    ))
                } else {
                    Result.failure(Exception(responseBody?.message ?: "Failed to fetch recruiter profile"))
                }
            } else {
                Result.failure(Exception("Failed to fetch recruiter profile"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}