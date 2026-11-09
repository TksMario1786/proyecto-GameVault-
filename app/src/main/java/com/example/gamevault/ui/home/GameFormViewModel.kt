package com.example.gamevault.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamevault.domain.model.Videojuego
import com.example.gamevault.domain.repository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameFormViewModel(
    private val gameRepository: GameRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameFormUiState())
    val uiState: StateFlow<GameFormUiState> = _uiState.asStateFlow()

    fun loadVideojuego(id: String?) {
        if (id == null) return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            gameRepository.getById(id).onSuccess { juego ->
                _uiState.update {
                    it.copy(
                        id = juego.id,
                        titulo = juego.titulo,
                        plataforma = juego.plataforma,
                        horasJugadas = juego.horasJugadas.toString(),
                        completado = juego.completado,
                        fechaAdquisicion = juego.fechaAdquisicion,
                        isLoading = false
                    )
                }
            }.onFailure { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun updateField(field: String, value: Any) {
        _uiState.update { currentState ->
            when (field) {
                "titulo" -> currentState.copy(titulo = value as String)
                "plataforma" -> currentState.copy(plataforma = value as String)
                "horasJugadas" -> currentState.copy(horasJugadas = value as String)
                "completado" -> currentState.copy(completado = value as Boolean)
                "fechaAdquisicion" -> currentState.copy(fechaAdquisicion = value as String)
                else -> currentState
            }
        }
    }

    fun saveVideojuego() {
        val state = _uiState.value
        if (state.titulo.isBlank() || state.plataforma.isBlank()) {
            _uiState.update { it.copy(error = "El título y la plataforma son obligatorios") }
            return
        }

        val videojuego = Videojuego(
            id = state.id,
            titulo = state.titulo,
            plataforma = state.plataforma,
            horasJugadas = state.horasJugadas.toIntOrNull() ?: 0,
            completado = state.completado,
            fechaAdquisicion = state.fechaAdquisicion
        )

        _uiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            val result = if (state.id.isEmpty()) {
                gameRepository.create(videojuego)
            } else {
                gameRepository.update(state.id, videojuego)
            }

            result.onSuccess {
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }
            }.onFailure { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}