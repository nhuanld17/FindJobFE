package com.example.findjob.viewmodel

import androidx.lifecycle.ViewModel
import com.example.findjob.utils.InfoManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val infoManager: InfoManager
) : ViewModel() {
    fun logout() {
        infoManager.clearTokens()
    }
} 