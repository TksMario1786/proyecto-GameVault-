package com.example.gamevault.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class VideojuegoDto(
    val id: String? = null,
    val titulo: String,
    val plataforma: String,
    val horasJugadas: Int,
    val completado: Boolean,
    val fechaAdquisicion: String
)