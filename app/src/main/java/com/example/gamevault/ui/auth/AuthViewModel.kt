package com.example.gamevault.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamevault.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun checkActiveSession(): Boolean {
        return authRepository.hasActiveSession()
    }

    fun login(email: String, pass: String) {
        if (email.isBlank() || pass.isBlank()) {
            _uiState.update { it.copy(error = "Llena todos los campos") }
            return
        }

        _uiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            val result = authRepository.login(email, pass)
            result.onSuccess {
                _uiState.update { state -> state.copy(isLoading = false, isSuccess = true) }
            }.onFailure { e ->
                _uiState.update { state -> state.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun register(nombre: String, email: String, pass: String, confirmPass: String) {
        if (nombre.isBlank() || email.isBlank() || pass.isBlank()) {
            _uiState.update { it.copy(error = "Llena todos los campos") }
            return
        }
        if (pass.length < 8) {
            _uiState.update { it.copy(error = "La contraseña debe tener al menos 8 caracteres") }
            return
        }
        if (pass != confirmPass) {
            _uiState.update { it.copy(error = "Las contraseñas no coinciden") }
            return
        }

        _uiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            val result = authRepository.register(nombre, email, pass)
            result.onSuccess {
                _uiState.update { state -> state.copy(isLoading = false, isSuccess = true) }
            }.onFailure { e ->
                _uiState.update { state -> state.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun resetState() {
        _uiState.update { AuthUiState() }
    }
}