package com.example.gamevault.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gamevault.GameVaultApplication
import com.example.gamevault.ui.auth.AuthViewModel
import com.example.gamevault.ui.home.GameFormViewModel
import com.example.gamevault.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer { AuthViewModel(authRepository = gameVaultApplication().container.authRepository) }
        initializer { HomeViewModel(gameRepository = gameVaultApplication().container.gameRepository, authRepository = gameVaultApplication().container.authRepository) }
        initializer { GameFormViewModel(gameRepository = gameVaultApplication().container.gameRepository) }
    }
}

fun CreationExtras.gameVaultApplication(): GameVaultApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GameVaultApplication)