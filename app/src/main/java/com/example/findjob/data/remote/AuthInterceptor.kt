package com.example.findjob.data.remote

import com.example.findjob.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
// tự động gắn access token vào mỗi request.

// tạo một Interceptor tùy chỉnh bằng cách implement interface Interceptor của OkHttp.
class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    /**
     * Đây là hàm bắt buộc phải override. Mỗi khi một request được gửi, hàm này sẽ được
     * gọi để can thiệp (intercept) trước khi request ra khỏi app.
     */
    override fun intercept(chain: Interceptor.Chain): Response {

        // lấy Request gốc và tạo ra một builder để sửa đổi nó (thêm headers chẳng hạn).
        val requestBuilder = chain.request().newBuilder()

        /**
         * Nếu accessToken không null, thì:
         * → thêm header: Authorization: Bearer abc123xyz
         */
        tokenManager.getAccessToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        // Gửi request đã được chỉnh sửa (có thêm header) tiếp theo trong chuỗi interceptor.
        return chain.proceed(requestBuilder.build())
    }
}