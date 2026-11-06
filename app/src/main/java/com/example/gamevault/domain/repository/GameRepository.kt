package com.example.gamevault.domain.repository

import com.example.gamevault.domain.model.Videojuego

interface GameRepository {
    suspend fun getAll(): Result<List<Videojuego>>
    suspend fun getById(id: String): Result<Videojuego>
    suspend fun create(videojuego: Videojuego): Result<Videojuego>
    suspend fun update(id: String, videojuego: Videojuego): Result<Videojuego>
    suspend fun delete(id: String): Result<Unit>
}