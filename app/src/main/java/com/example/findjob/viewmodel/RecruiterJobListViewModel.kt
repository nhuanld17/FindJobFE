package com.example.findjob.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findjob.data.model.response.ListJobResponse
import com.example.findjob.data.repository.JobPostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class RecruiterJobListViewModel @Inject constructor(
    private val repository: JobPostRepository
) : ViewModel() {
    private val _state = MutableStateFlow<JobListState>(JobListState.Idle)
    val state: StateFlow<JobListState> = _state.asStateFlow()

    init {
        getJobPosts()
    }

    fun getJobPosts() {
        viewModelScope.launch {
            try {
                _state.value = JobListState.Loading
                repository.getJobPosts()
                    .onSuccess { jobs ->
                        println("Total jobs received: ${jobs.size}")
                        
                        // Phân loại jobs dựa trên expirateAt
                        val activeJobs = jobs.filter { job ->
                            // Nếu expirateAt là null hoặc rỗng, coi như job còn hạn
                            if (job.expirateAt.isNullOrEmpty()) {
                                return@filter true
                            }
                            
                            try {
                                val currentDate = LocalDate.now()
                                val expiryDate = LocalDate.parse(job.expirateAt)
                                // Job còn hạn nếu ngày hết hạn sau ngày hiện tại
                                expiryDate.isAfter(currentDate)
                            } catch (e: Exception) {
                                println("Error parsing date for job ${job.title}: ${e.message}")
                                // Nếu có lỗi parse date, coi như job còn hạn
                                true
                            }
                        }
                        
                        val expiredJobs = jobs.filter { job ->
                            // Nếu expirateAt là null hoặc rỗng, coi như job còn hạn
                            if (job.expirateAt.isNullOrEmpty()) {
                                return@filter false
                            }
                            
                            try {
                                val currentDate = LocalDate.now()
                                val expiryDate = LocalDate.parse(job.expirateAt)
                                // Job hết hạn nếu ngày hết hạn trước hoặc bằng ngày hiện tại
                                !expiryDate.isAfter(currentDate)
                            } catch (e: Exception) {
                                println("Error parsing date for job ${job.title}: ${e.message}")
                                // Nếu có lỗi parse date, coi như job còn hạn
                                false
                            }
                        }
                        
                        println("Active jobs: ${activeJobs.size}")
                        println("Expired jobs: ${expiredJobs.size}")
                        
                        _state.value = JobListState.Success(activeJobs, expiredJobs)
                    }
                    .onFailure { error ->
                        println("Error fetching jobs: ${error.message}")
                        _state.value = JobListState.Error(error.message ?: "Failed to fetch jobs")
                    }
            } catch (e: Exception) {
                println("Unexpected error: ${e.message}")
                _state.value = JobListState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}

sealed class JobListState {
    object Idle : JobListState()
    object Loading : JobListState()
    data class Success(
        val activeJobs: List<ListJobResponse>,
        val expiredJobs: List<ListJobResponse>
    ) : JobListState()
    data class Error(val message: String) : JobListState()
}