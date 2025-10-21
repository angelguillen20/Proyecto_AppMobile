package com.pokeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pokeapp.data.AppState
import com.pokeapp.view.LoginScreen
import com.pokeapp.view.MenuScreen
import com.pokeapp.view.RegistroScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    appState: AppState
) {
    NavHost(
        navController = navController, startDestination = "login",
    ) {
        composable("login") { LoginScreen(navController, appState) }
        composable("registro") { RegistroScreen(navController, appState) }
        composable("menu") { MenuScreen(navController, appState) }
        // Acá agregar más rutas para cada pantalla, como Perfil y categorías
    }

}
