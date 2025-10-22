package com.pokeapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pokeapp.data.AppState
import com.pokeapp.data.CartViewModel
import com.pokeapp.view.CarritoScreen
import com.pokeapp.view.LoginScreen
import com.pokeapp.view.MenuScreen
import com.pokeapp.view.PagoScreen
import com.pokeapp.view.*
import com.pokeapp.view.RegistroScreen
import com.pokeapp.view.confirmarPago
import com.pokeapp.viewimport.QRScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    appState: AppState

) {
    val cartViewModel: CartViewModel = viewModel()

    NavHost(
        navController = navController, startDestination = "login",
    ) {
        composable("login") { LoginScreen(navController, appState) }
        composable("registro") { RegistroScreen(navController, appState) }
        composable("menu") { MenuScreen(navController, appState, cartViewModel) }
        composable("carrito"){ CarritoScreen(viewModel = cartViewModel, navController = navController,onBack = {navController.popBackStack()}) }
        composable("pago"){ PagoScreen(cartViewModel,onBack = {navController.popBackStack()},
            navController = navController, onConfirm = {navController.popBackStack()}) }
        composable("confirmarPago"){ confirmarPago(navController) }
        composable("qr"){ QRScreen(navController) }
        // Acá agregar más rutas para cada pantalla, como Perfil y categorías
    }

}
