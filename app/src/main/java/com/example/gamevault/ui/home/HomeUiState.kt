package com.example.gamevault.ui.home

import com.example.gamevault.domain.model.Videojuego

data class HomeUiState(
    val isLoading: Boolean = false,
    val videojuegos: List<Videojuego> = emptyList(),
    val error: String? = null
)