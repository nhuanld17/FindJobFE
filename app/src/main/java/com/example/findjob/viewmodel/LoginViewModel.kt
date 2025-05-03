package com.example.findjob.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findjob.data.repository.AuthRepository
import com.example.findjob.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                TokenManager.accessToken = response.accessToken
                TokenManager.refreshToken = response.refreshToken
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}