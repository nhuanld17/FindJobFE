package com.example.findjob.data.remote.api

import com.example.findjob.data.model.common.RestResponse
import com.example.findjob.data.model.request.JobPostRequest
import com.example.findjob.data.model.response.ListJobResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface JobPostApi {
    @POST("jobpost/create")
    suspend fun createJobPost(@Body jobPostRequest: JobPostRequest) : Response<RestResponse<JobPostRequest>>

    @GET("jobpost/list-recent")
    suspend fun getJobPostsRecent() : Response<RestResponse<List<ListJobResponse>>>

    @GET("jobpost/list")
    suspend fun getJobPosts() : Response<RestResponse<List<ListJobResponse>>>
}