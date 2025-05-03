package com.example.findjob.data.remote

import okhttp3.Interceptor

class CookieInterceptor(private val refreshToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Cookie", "refresh_token=$refreshToken") // 👈 Quan trọng!
            .build()
        return chain.proceed(newRequest)
    }
}