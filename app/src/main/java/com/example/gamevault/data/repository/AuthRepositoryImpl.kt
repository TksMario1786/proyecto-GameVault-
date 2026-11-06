package com.example.gamevault.data.repository

import com.example.gamevault.data.local.SessionManager
import com.example.gamevault.data.remote.api.GameVaultApi
import com.example.gamevault.data.remote.dto.AuthRequestDto
import com.example.gamevault.domain.model.UserContext
import com.example.gamevault.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: GameVaultApi,
    private val sessionManager: SessionManager
) : AuthRepository {

    override suspend fun login(email: String, pass: String): Result<UserContext> {
        return try {
            val response = api.login(AuthRequestDto(email = email, password = pass))
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                sessionManager.saveToken(body.accessToken)
                Result.success(UserContext(body.accessToken, body.user.email))
            } else {
                Result.failure(Exception("Credenciales inválidas"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(nombre: String, email: String, pass: String): Result<UserContext> {
        return try {
            val response = api.register(AuthRequestDto(email = email, password = pass, nombre = nombre))
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                sessionManager.saveToken(body.accessToken)
                Result.success(UserContext(body.accessToken, body.user.email))
            } else {
                Result.failure(Exception("Error al registrar usuario"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun logout() {
        sessionManager.clearSession()
    }

    override fun hasActiveSession(): Boolean {
        return !sessionManager.getToken().isNullOrEmpty()
    }
}
