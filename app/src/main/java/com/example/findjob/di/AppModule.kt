package com.example.findjob.di

import android.content.Context
import com.example.findjob.data.remote.api.AuthApi
import com.example.findjob.data.repository.AuthRepository
import com.example.findjob.utils.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthApi,
        tokenManager: TokenManager
    ): AuthRepository {
        return AuthRepository(api, tokenManager)
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context
}
