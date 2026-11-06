package com.example.gamevault.domain.repository

import com.example.gamevault.domain.model.UserContext

interface AuthRepository {
    suspend fun login(email: String, pass: String): Result<UserContext>
    suspend fun register(nombre: String, email: String, pass: String): Result<UserContext>
    fun logout()
    fun hasActiveSession(): Boolean
}