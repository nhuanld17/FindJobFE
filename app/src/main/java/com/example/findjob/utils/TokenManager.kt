package com.example.findjob.utils

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    private val TAG = "TokenManager"

    var accessToken: String?
        get() = prefs.getString("access_token", null)
        set(value) {
            Log.d(TAG, "Setting access token: ${value?.take(10)}...")
            prefs.edit().putString("access_token", value).apply()
        }

    var refreshToken: String?
        get() = prefs.getString("refresh_token", null)
        set(value) {
            Log.d(TAG, "Setting refresh token: ${value?.take(10)}...")
            prefs.edit().putString("refresh_token", value).apply()
        }

    fun clear() {
        Log.d(TAG, "Clearing tokens")
        prefs.edit().clear().apply()
    }

    fun hasValidTokens(): Boolean {
        return !accessToken.isNullOrEmpty() && !refreshToken.isNullOrEmpty()
    }
}
