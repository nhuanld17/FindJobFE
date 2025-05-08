package com.example.findjob.data.remote.api

import com.example.findjob.data.model.common.RestResponse
import com.example.findjob.data.model.response.EmployeeProfileDTO
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeApi {
    @GET("employee/profile")
    suspend fun getEmployeeProfile() : Response<RestResponse<EmployeeProfileDTO>>
}