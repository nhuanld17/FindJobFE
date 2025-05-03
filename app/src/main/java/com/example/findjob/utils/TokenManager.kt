package com.example.findjob.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

// 1
/**
 * Mục đích của TokenManager:
 * - Lưu access token
 * - Lấy token ra để gắn vào request
 * - Xoá token khi logout
 * - Kiểm tra xem token có còn hợp lệ không
 */

/**
 * @Singleton: app chỉ tạo một instance duy nhất của TokenManager
 * @Inject: cho phép Hilt/Dagger tiêm tự động khi cần
 * @ApplicationContext: chỉ rõ là context của toàn ứng dụng, không phải activity
 */
@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    /**
     * - SharedPreferences: Cách đơn giản nhất để lưu trữ dữ liệu nhỏ dạng key–value.
     * - "auth_prefs" là tên file lưu trữ token.
     * - MODE_PRIVATE: Chỉ app của bạn mới truy cập được file này.
     */
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "auth_prefs",
        Context.MODE_PRIVATE
    )

    companion object {
        private const val TAG = "TokenManager"
    }

    // Hàm lưu token
    fun saveTokens(accessToken: String) {
        // Ghi log (logcat) 10 ký tự đầu tiên của token để tiện debug.
        Log.d(TAG, "Setting access token: ${accessToken.take(10)}...")

        // Dùng putString(...) để lưu token vào SharedPreferences.
        // Gọi apply() để lưu bất đồng bộ (nhanh, không block UI).
        sharedPreferences.edit()
            .putString("access_token", accessToken)
            .apply()
    }

    // Trả về token nếu có, hoặc null nếu chưa từng lưu.
    fun getAccessToken(): String? = sharedPreferences.getString("access_token", null)

    // Xoá toàn bộ key–value trong auth_prefs, tức là xoá access token.
    fun clearTokens() {
        Log.d(TAG, "Clearing tokens")
        sharedPreferences.edit()
            .remove("access_token")
            .apply()
    }

    // Kiểm tra token hợp lệ: Trả về true nếu token không rỗng → tức là user đã login thành công.
    fun isLoggedIn(): Boolean {
        return !getAccessToken().isNullOrEmpty()
    }
}
