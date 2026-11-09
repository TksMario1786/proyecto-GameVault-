package com.example.gamevault.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gamevault.ui.AppViewModelProvider
import com.example.gamevault.ui.auth.AuthViewModel
import com.example.gamevault.ui.auth.LoginScreen
import com.example.gamevault.ui.auth.RegisterScreen
import com.example.gamevault.ui.home.GameFormScreen
import com.example.gamevault.ui.home.GameFormViewModel
import com.example.gamevault.ui.home.HomeScreen
import com.example.gamevault.ui.home.HomeViewModel

@Composable
fun GameVaultNavGraph(navController: NavHostController) {
    val authViewModel: AuthViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val startDestination = if (authViewModel.checkActiveSession()) Screen.Home.route else Screen.Login.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) { popUpTo(Screen.Login.route) { inclusive = true } }
                },
                onNavigateToRegister = { navController.navigate(Screen.Register.route) }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                viewModel = authViewModel,
                onRegisterSuccess = {
                    navController.navigate(Screen.Home.route) { popUpTo(Screen.Login.route) { inclusive = true } }
                },
                onNavigateToLogin = { navController.popBackStack() }
            )
        }
        composable(Screen.Home.route) {
            val homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
            HomeScreen(
                viewModel = homeViewModel,
                onNavigateToForm = { gameId -> navController.navigate(Screen.GameForm.createRoute(gameId)) },
                onLogout = {
                    navController.navigate(Screen.Login.route) { popUpTo(Screen.Home.route) { inclusive = true } }
                }
            )
        }
        composable(
            route = Screen.GameForm.route,
            arguments = listOf(navArgument("gameId") { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            val gameFormViewModel: GameFormViewModel = viewModel(factory = AppViewModelProvider.Factory)
            GameFormScreen(
                viewModel = gameFormViewModel,
                gameId = backStackEntry.arguments?.getString("gameId"),
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}