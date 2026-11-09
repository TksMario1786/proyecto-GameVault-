package com.example.gamevault.ui.home

data class GameFormUiState(
    val id: String = "",
    val titulo: String = "",
    val plataforma: String = "",
    val horasJugadas: String = "",
    val completado: Boolean = false,
    val fechaAdquisicion: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)