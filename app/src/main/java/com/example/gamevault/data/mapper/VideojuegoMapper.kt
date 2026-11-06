package com.example.gamevault.data.mapper

import com.example.gamevault.data.remote.dto.VideojuegoDto
import com.example.gamevault.domain.model.Videojuego

fun VideojuegoDto.toDomain(): Videojuego {
    return Videojuego(
        id = this.id ?: "",
        titulo = this.titulo,
        plataforma = this.plataforma,
        horasJugadas = this.horasJugadas,
        completado = this.completado,
        fechaAdquisicion = this.fechaAdquisicion
    )
}

fun Videojuego.toDto(): VideojuegoDto {
    return VideojuegoDto(
        id = if (this.id.isEmpty()) null else this.id,
        titulo = this.titulo,
        plataforma = this.plataforma,
        horasJugadas = this.horasJugadas,
        completado = this.completado,
        fechaAdquisicion = this.fechaAdquisicion
    )
}