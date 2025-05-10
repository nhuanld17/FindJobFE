package com.example.findjob.data.repository

import com.example.findjob.data.model.request.JobPostRequest
import com.example.findjob.data.model.response.ListJobResponse
import com.example.findjob.data.remote.api.JobPostApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobPostRepository @Inject constructor(
    private val jobPostApi: JobPostApi
) {
    suspend fun createJobPost(jobPostRequest: JobPostRequest): Result<Unit> {
        return try {
            val response = jobPostApi.createJobPost(jobPostRequest)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody?.statusCode in 200..299) {
                    Result.success(Unit)
                } else {
                    Result.failure(Exception(responseBody?.message ?: "Failed to create job post"))
                }
            } else {
                Result.failure(Exception("Failed to create job post"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getJobPosts(): Result<List<ListJobResponse>> {
        return try {
            val response = jobPostApi.getJobPosts()
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody?.statusCode in 200..299) {
                    Result.success(responseBody?.data ?: emptyList())
                } else {
                    Result.failure(Exception(responseBody?.message ?: "Failed to fetch job posts"))
                }
            } else {
                Result.failure(Exception("Failed to fetch job posts"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getJobPostsRecent(): Result<List<ListJobResponse>> {
        return try {
            val response = jobPostApi.getJobPostsRecent()
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody?.statusCode in 200..299) {
                    Result.success(responseBody?.data ?: emptyList())
                } else {
                    Result.failure(Exception(responseBody?.message ?: "Failed to fetch recent job posts"))
                }
            } else {
                Result.failure(Exception("Failed to fetch recent job posts"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}