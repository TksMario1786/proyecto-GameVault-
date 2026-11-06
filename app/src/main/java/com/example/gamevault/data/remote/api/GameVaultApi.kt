package com.example.gamevault.data.remote.api

import com.example.gamevault.data.remote.dto.AuthRequestDto
import com.example.gamevault.data.remote.dto.AuthResponseDto
import com.example.gamevault.data.remote.dto.VideojuegoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GameVaultApi {
    @POST("register")
    suspend fun register(@Body request: AuthRequestDto): Response<AuthResponseDto>

    @POST("login")
    suspend fun login(@Body request: AuthRequestDto): Response<AuthResponseDto>

    @GET("videojuegos")
    suspend fun getVideojuegos(): Response<List<VideojuegoDto>>

    @GET("videojuegos/{id}")
    suspend fun getVideojuegoById(@Path("id") id: String): Response<VideojuegoDto>

    @POST("videojuegos")
    suspend fun createVideojuego(@Body videojuego: VideojuegoDto): Response<VideojuegoDto>

    @PUT("videojuegos/{id}")
    suspend fun updateVideojuego(@Path("id") id: String, @Body videojuego: VideojuegoDto): Response<VideojuegoDto>

    @DELETE("videojuegos/{id}")
    suspend fun deleteVideojuego(@Path("id") id: String): Response<Unit>
}