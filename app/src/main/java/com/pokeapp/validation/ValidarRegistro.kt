package com.pokeapp.validation

import com.pokeapp.data.AppState

fun registroExitoso(usuario : String, password : String, confirmPassword : String, appState : AppState):String {

  var error: String
  when{
    usuario.isBlank()|| password.isBlank() ||confirmPassword.isBlank() ->
      error = "TODOS los campos SON Obligatorios"
    usuario.length < 2 ->
      error = "El usuario debe tener al menos 2 caracteres"
    password.length < 4 ->
      error = "La contraseña debe tener al menos 4 caracteres"
    password != confirmPassword ->
      error = "Las contraseñas NO coinciden"
    !appState.registrarUsuario(usuario, password) ->
      error = "El usuario YA existe"
    else -> {
      error = ""
    }
  }
  return error
}