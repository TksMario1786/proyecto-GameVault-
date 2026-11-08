package com.example.gamevault.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamevault.domain.repository.AuthRepository
import com.example.gamevault.domain.repository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val gameRepository: GameRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchVideojuegos()
    }

    fun fetchVideojuegos() {
        _uiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            gameRepository.getAll().onSuccess { lista ->
                _uiState.update { it.copy(isLoading = false, videojuegos = lista) }
            }.onFailure { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun deleteVideojuego(id: String) {
        viewModelScope.launch {
            gameRepository.delete(id).onSuccess {
                fetchVideojuegos()
            }
        }
    }

    fun logout() {
        authRepository.logout()
    }
}