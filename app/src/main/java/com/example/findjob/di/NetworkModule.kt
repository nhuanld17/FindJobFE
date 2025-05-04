package com.example.findjob.di

import com.example.findjob.data.remote.api.AuthApi
import com.example.findjob.data.remote.interceptor.AuthInterceptor
import com.example.findjob.data.remote.interceptor.TokenAuthenticator
import com.example.findjob.utils.InfoManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Nơi cấu hình Retrofit, OkHttp, và các thành phần cần thiết để gọi API trong ứng dụng
 * Android.
 * @Module: Đánh dấu đây là nơi cung cấp các dependency
 * @InstallIn(SingletonComponent::class): Các dependency sống suốt vòng đời của app
 * object: Dùng object thay vì class vì ta không cần nhiều instance
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Địa chỉ server (dùng 10.0.2.2 thay vì localhost để Android Emulator
    // truy cập máy tính của bạn)
    private const val BASE_URL = "http://192.168.1.6:8080/api/"

    /**
     * Dùng để ghi log request & response khi bạn gọi API.
     * Tạo interceptor để ghi lại toàn bộ thông tin request/response
     * Level.BODY: Ghi cả header và body của request/response
     */
    // @Provides: Hilt sẽ gọi hàm này để tạo HttpLoggingInterceptor.
    // @Singleton: Tạo 1 lần duy nhất cho toàn app.
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * @Provides: Hilt sẽ gọi hàm này để tạo AuthInterceptor.
     * @Singleton: Tạo 1 lần duy nhất cho toàn app.
     */
    @Provides
    @Singleton
    fun provideAuthInterceptor(infoManager: InfoManager): AuthInterceptor = AuthInterceptor(infoManager)

    @Provides
    @Singleton
    fun provideTokenAuthenticator(infoManager: InfoManager): TokenAuthenticator {
        return TokenAuthenticator(infoManager)
    }

    /**
     * Retrofit – Thư viện gọi API
     * BASE_URL: là địa chỉ API gốc (ở đây là máy chủ backend chạy trên máy thật).
     * addConverterFactory(...): giúp Retrofit hiểu dữ liệu JSON.
     */
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    /**
     * OkHttpClient – Cấu hình client mạng tổng thể
     * - addInterceptor(logging): In log JSON request/response
     * - addInterceptor(authInterceptor): Gắn access token vào header
     * - authenticator(tokenAuthenticator): Lỗi token hết hạn thì yêu cầu đăng nhập lại
     * - timeout: Cài thời gian chờ (30 giây) cho các loại kết nối
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .authenticator(tokenAuthenticator)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
}
