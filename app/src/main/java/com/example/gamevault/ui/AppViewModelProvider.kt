package com.example.gamevault.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gamevault.GameVaultApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
    }
}

fun CreationExtras.gameVaultApplication(): GameVaultApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GameVaultApplication)
