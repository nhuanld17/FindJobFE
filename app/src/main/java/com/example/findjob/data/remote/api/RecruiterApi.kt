package com.example.findjob.data.remote.api

import com.example.findjob.data.model.common.RestResponse
import com.example.findjob.data.model.request.RecruiterRequest
import com.example.findjob.data.model.response.RecruiterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RecruiterApi {
    @POST("recruiter/update/profile")
    suspend fun updateRecruiterProfile(
        @Body recruiterRequest: RecruiterRequest
    ): Response<RestResponse<RecruiterResponse>>

    @GET("recruiter/get/profile")
    suspend fun getRecruiterProfile(): Response<RestResponse<RecruiterResponse>>
}
