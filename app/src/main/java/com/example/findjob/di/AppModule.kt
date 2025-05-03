package com.example.findjob.di

import com.example.findjob.data.remote.AuthApi
import com.example.findjob.data.repository.AuthRepository
import com.example.findjob.utils.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthApi,
        tokenManager: TokenManager
    ): AuthRepository {
        return AuthRepository(api, tokenManager)
    }
}
