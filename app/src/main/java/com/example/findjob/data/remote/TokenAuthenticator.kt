package com.example.findjob.data.remote

import com.example.findjob.utils.TokenManager
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        // Chỉ trả về null để OkHttp biết rằng không thể refresh token
        // Việc refresh token sẽ được xử lý ở nơi khác
        return null
    }
}