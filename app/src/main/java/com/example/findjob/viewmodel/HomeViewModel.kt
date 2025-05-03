package com.example.findjob.viewmodel

import androidx.lifecycle.ViewModel
import com.example.findjob.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val tokenManager: TokenManager
) : ViewModel() {
    fun logout() {
        tokenManager.clearTokens()
    }
} 