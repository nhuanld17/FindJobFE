package com.example.findjob.data.repository

import com.example.findjob.data.model.request.LoginRequest
import com.example.findjob.data.model.response.EmployeeProfileDTO
import com.example.findjob.data.remote.api.EmployeeApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRepository @Inject constructor(
    private val employeeApi: EmployeeApi
){
    suspend fun getEmployeeProfile(): Result<EmployeeProfileDTO> {
        return try {
            val response = employeeApi.getEmployeeProfile()
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody?.statusCode in 200..299) {
                    Result.success(responseBody?.data!!)
                } else {
                    Result.failure(Exception(responseBody?.message ?: "Failed to get profile"))
                }
            } else {
                Result.failure(Exception("Failed to get profile"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}