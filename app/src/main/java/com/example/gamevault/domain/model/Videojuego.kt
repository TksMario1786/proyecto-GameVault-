package com.example.gamevault.domain.model

data class Videojuego(
    val id: String = "",
    val titulo: String,
    val plataforma: String,
    val horasJugadas: Int,
    val completado: Boolean,
    val fechaAdquisicion: String
)