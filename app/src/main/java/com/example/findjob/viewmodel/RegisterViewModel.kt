package com.example.findjob.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findjob.data.repository.AuthRepository
import com.example.findjob.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    fun register(name: String, email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.register(name, email, password)
                TokenManager.accessToken = response.accessToken
                TokenManager.refreshToken = response.refreshToken
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}