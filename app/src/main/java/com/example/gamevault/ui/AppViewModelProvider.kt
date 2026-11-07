package com.example.gamevault.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gamevault.GameVaultApplication
import com.example.gamevault.ui.auth.AuthViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AuthViewModel(
                authRepository = gameVaultApplication().container.authRepository
            )
        }
    }
}

fun CreationExtras.gameVaultApplication(): GameVaultApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GameVaultApplication)