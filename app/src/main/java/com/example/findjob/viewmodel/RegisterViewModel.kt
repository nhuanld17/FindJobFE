package com.example.findjob.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findjob.data.model.AuthResponse
import com.example.findjob.data.repository.AuthRepository
import com.example.findjob.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    fun register(role: String,username: String, email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            repository.register(role,username, email, password)
                .onSuccess { authResponse ->
                    _registerState.value = RegisterState.Success(authResponse)
                }
                .onFailure { error ->
                    _registerState.value = RegisterState.Error(error.message ?: "Unknown error")
                }
        }
    }
}

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val authResponse: AuthResponse) : RegisterState()
    data class Error(val message: String) : RegisterState()
}