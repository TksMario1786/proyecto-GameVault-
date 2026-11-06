package com.example.gamevault.di

import android.content.Context
import com.example.gamevault.data.local.SessionManager
import com.example.gamevault.data.local.SessionManagerImpl
import com.example.gamevault.data.remote.api.GameVaultApi
import com.example.gamevault.data.remote.interceptor.AuthInterceptor
import com.example.gamevault.data.repository.AuthRepositoryImpl
import com.example.gamevault.data.repository.GameRepositoryImpl
import com.example.gamevault.domain.repository.AuthRepository
import com.example.gamevault.domain.repository.GameRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface AppContainer {
    val authRepository: AuthRepository
    val gameRepository: GameRepository
    val sessionManager: SessionManager
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    override val sessionManager: SessionManager by lazy {
        SessionManagerImpl(context)
    }

    private val authInterceptor by lazy {
        AuthInterceptor(sessionManager)
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    private val baseUrl = "http://10.0.2.2:3000/"
    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }

    private val retrofitService: GameVaultApi by lazy {
        retrofit.create(GameVaultApi::class.java)
    }

    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(retrofitService, sessionManager)
    }

    override val gameRepository: GameRepository by lazy {
        GameRepositoryImpl(retrofitService)
    }
}