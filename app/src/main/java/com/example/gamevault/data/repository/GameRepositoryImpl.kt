package com.example.gamevault.data.repository

import com.example.gamevault.data.mapper.toDomain
import com.example.gamevault.data.mapper.toDto
import com.example.gamevault.data.remote.api.GameVaultApi
import com.example.gamevault.domain.model.Videojuego
import com.example.gamevault.domain.repository.GameRepository

class GameRepositoryImpl(
    private val api: GameVaultApi
) : GameRepository {

    override suspend fun getAll(): Result<List<Videojuego>> {
        return try {
            val response = api.getVideojuegos()
            if (response.isSuccessful) {
                Result.success(response.body()?.map { it.toDomain() } ?: emptyList())
            } else {
                Result.failure(Exception("Error al obtener la lista"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getById(id: String): Result<Videojuego> {
        return try {
            val response = api.getVideojuegoById(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.toDomain())
            } else {
                Result.failure(Exception("Error al obtener el detalle"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun create(videojuego: Videojuego): Result<Videojuego> {
        return try {
            val response = api.createVideojuego(videojuego.toDto())
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.toDomain())
            } else {
                Result.failure(Exception("Error al crear el registro"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun update(id: String, videojuego: Videojuego): Result<Videojuego> {
        return try {
            val response = api.updateVideojuego(id, videojuego.toDto())
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.toDomain())
            } else {
                Result.failure(Exception("Error al actualizar el registro"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun delete(id: String): Result<Unit> {
        return try {
            val response = api.deleteVideojuego(id)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error al eliminar el registro"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}