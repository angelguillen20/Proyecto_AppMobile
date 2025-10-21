package com.pokeapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pokeapp.data.AppState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pokeapp.ui.theme.pkmnFont
import com.pokeapp.validation.registroExitoso
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(navController: NavHostController, appState: AppState){

  var usuario by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var confirmPassword by remember { mutableStateOf("") }
  var error by remember { mutableStateOf("") }



  // Arreglar esto mañana, tengo que editar esto con el loginScreen que creé
  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = Color.Transparent,
          titleContentColor = Color.DarkGray,
        ),
        title = {
          Text(
            text = "PokéShop",
            fontFamily = pkmnFont,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
          )
        }
      )
    }
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(16.dp),
      verticalArrangement = Arrangement.Center
    ) {
      Text(text = "Registro",
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        color = Color.DarkGray)

      OutlinedTextField(
        value = usuario,
        onValueChange = { usuario = it },
        label = { Text("Nombre de usuario")},
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
          focusedBorderColor = Color.Gray,
          unfocusedBorderColor = Color.LightGray
        )
      )
      Spacer(Modifier.height(8.dp))
      OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Contraseña")},
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
          focusedBorderColor = Color.Gray,
          unfocusedBorderColor = Color.LightGray
        )
      )
      Spacer(Modifier.height(8.dp))
      OutlinedTextField(
        value = confirmPassword,
        onValueChange = { confirmPassword = it },
        label = { Text("Confirmar Contraseña")},
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
          focusedBorderColor = Color.Gray,
          unfocusedBorderColor = Color.LightGray
        )
      )
      Spacer(Modifier.height(16.dp))

      if (error.isNotEmpty()){
        Text(error, color = MaterialTheme.colorScheme.error)
        Spacer(Modifier.height(8.dp))
      }
      Button(
        onClick = {
          error = registroExitoso(usuario,password,confirmPassword,appState)
          if(error==""){
            navController.navigate("login")
          }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
          containerColor = Color.DarkGray
        )
      ) {
        Text("Registrarse",
          color = Color.White,
          fontWeight = FontWeight.Bold,
          fontSize = 18.sp)
      }
      Spacer(Modifier.height(8.dp))
      TextButton(
        onClick = { navController.navigate("login") },
        modifier = Modifier.align(Alignment.CenterHorizontally)
      ) {
        Text(
          "¿Tienes una cuenta? Inicia Sesión",
          color = Color.Blue
        )
      }

    }}
}