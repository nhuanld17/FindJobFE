package com.example.findjob.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findjob.data.model.request.JobPostRequest
import com.example.findjob.data.repository.JobPostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobPostViewModel @Inject constructor(
    private val repository: JobPostRepository
) : ViewModel() {
    private val _state = MutableStateFlow<JobPostState>(JobPostState.Idle)
    val state: StateFlow<JobPostState> = _state.asStateFlow()

    fun createJobPost(
        title: String,
        description: String,
        position: String,
        qualification: String,
        experience: String,
        type: String,
        salary: String,
        workType: String
    ) {
        viewModelScope.launch {
            _state.value = JobPostState.Loading
            val request = JobPostRequest(
                title = title,
                description = description,
                position = position,
                qualification = qualification,
                experience = experience,
                type = type,
                salary = salary,
                expirateAt = "2025-05-30T23:59:59"
            )
            
            repository.createJobPost(request)
                .onSuccess {
                    _state.value = JobPostState.Success("Job post created successfully!")
                }
                .onFailure { error ->
                    _state.value = JobPostState.Error(error.message ?: "Failed to create job post")
                }
        }
    }
}

sealed class JobPostState {
    object Idle : JobPostState()
    object Loading : JobPostState()
    data class Success(val message: String) : JobPostState()
    data class Error(val message: String) : JobPostState()
}