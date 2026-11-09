package com.example.gamevault.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object GameForm : Screen("game_form?gameId={gameId}") {
        fun createRoute(gameId: String?) = if (gameId != null) "game_form?gameId=$gameId" else "game_form"
    }
}