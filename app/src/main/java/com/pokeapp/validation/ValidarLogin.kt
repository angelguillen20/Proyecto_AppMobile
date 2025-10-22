package com.pokeapp.validation

import com.pokeapp.data.AppState

fun iniciarSesionValidacion(usuario: String, password: String, appState: AppState): String {
    var error = ""
    if (usuario.isBlank() || password.isBlank()) {
        error = "Debe ingresar usuario y contraseña"
    } else if (usuario.length < 2) {
        error = "El usuario debe tener al menos 2 caracteres"
    } else if (password.length < 4) {
        error = "La contraseña debe tener al menos 4 caracteres"
    } else if (!appState.login(usuario, password)) {
        error = "Usuario y/o contraseña incorrectos"
    }
    return error
}