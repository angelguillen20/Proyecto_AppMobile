package com.pokeapp.data

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class Usuario(val email: String, val password: String)

class AppState (private val dataStore: DataStoreManager){
  val usuarios = mutableStateListOf<Usuario>()
  var usuarioActual: Usuario? = null

  private val scope = CoroutineScope(Dispatchers.IO)

  fun cargarDatos(){
    scope.launch{
      val users = dataStore.getUsers().first()

      usuarios.clear()
      usuarios.addAll(users)
    }
  }

  fun registrarUsuario(email: String, password: String): Boolean{
    if (usuarios.any{ it.email == email}) return false
    val nuevo = Usuario(email,password)
    usuarios.add(nuevo)
    guardarUsuarios()
    return true
  }

  fun login(email: String,password: String): Boolean{
    var user = usuarios.find { it.email == email && it.password == password }
    return if ( user != null){
      usuarioActual = user
      true
    }else false
  }

  fun logout(){
    usuarioActual = null
  }

  private fun guardarUsuarios(){
    scope.launch {
      dataStore.saveUsers(usuarios)
    }
  }


}