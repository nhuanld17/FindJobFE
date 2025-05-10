package com.example.findjob.data.model.request

data class JobPostRequest(
    val title: String,
    val description: String,
    val position: String,
    val qualification: String,
    val experience: String,
    val type: String,
    val salary: String,
    val expirateAt: String,
)


