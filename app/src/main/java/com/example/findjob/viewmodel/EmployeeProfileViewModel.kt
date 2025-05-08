package com.example.findjob.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findjob.data.model.response.EmployeeProfileDTO
import com.example.findjob.data.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeProfileViewModel @Inject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {
    private val _state = MutableStateFlow<ProfileState>(ProfileState.Idle)
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    fun getProfile() {
        viewModelScope.launch {
            _state.value = ProfileState.Loading
            repository.getEmployeeProfile()
                .onSuccess { profile ->
                    _state.value = ProfileState.Success(profile)
                }
                .onFailure { error ->
                    _state.value = ProfileState.Error(error.message ?: "Unknown error")
                }
        }
    }
}

sealed class ProfileState {
    object Idle : ProfileState()
    object Loading : ProfileState()
    data class Success(val profile: EmployeeProfileDTO) : ProfileState()
    data class Error(val message: String) : ProfileState()
}