package com.example.findjob.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findjob.data.model.request.RecruiterRequest
import com.example.findjob.data.model.response.RecruiterResponse
import com.example.findjob.data.repository.RecruiterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ProfileRecruiterState {
    object Idle : ProfileRecruiterState()
    object Loading : ProfileRecruiterState()
    object Success : ProfileRecruiterState()
    object UpdateSuccess : ProfileRecruiterState()
    data class Error(val message: String) : ProfileRecruiterState()
}

@HiltViewModel
class RecruiterProfileViewModel @Inject constructor(
    private val repository: RecruiterRepository
) : ViewModel() {
    private val _state = MutableStateFlow<ProfileRecruiterState>(ProfileRecruiterState.Idle)
    val state: StateFlow<ProfileRecruiterState> = _state.asStateFlow()

    private val _profile = MutableStateFlow<RecruiterResponse?>(null)
    val profile: StateFlow<RecruiterResponse?> = _profile.asStateFlow()

    init {
        getProfile()
    }

    fun getProfile() {
        viewModelScope.launch {
            _state.value = ProfileRecruiterState.Loading
            repository.getRecruiterProfile()
                .onSuccess { response ->
                    _profile.value = response
                    _state.value = ProfileRecruiterState.Success
                }
                .onFailure { error ->
                    _state.value = ProfileRecruiterState.Error(error.message ?: "Failed to fetch profile")
                }
        }
    }

    fun updateProfile(
        about: String,
        website: String,
        industry: String,
        location: String,
        since: String,
        specialization: String
    ) {
        viewModelScope.launch {
            _state.value = ProfileRecruiterState.Loading
            val request = RecruiterRequest(
                about = about,
                website = website,
                industry = industry,
                location = location,
                since = since,
                specialization = specialization
            )

            repository.updateRecruiterProfile(request)
                .onSuccess {
                    _state.value = ProfileRecruiterState.UpdateSuccess
                    kotlinx.coroutines.delay(2000)
                    getProfile()
                }
                .onFailure { error ->
                    _state.value = ProfileRecruiterState.Error(error.message ?: "Failed to update profile")
                }
        }
    }
}