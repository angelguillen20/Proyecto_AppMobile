package com.pokeapp.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pokeapp.data.AppState
import com.pokeapp.ui.theme.pkmnFont
import com.pokeapp.validation.iniciarSesionValidacion

@OptIn(ExperimentalMaterial3Api::class)

// @Composable permite trabajar la función como una función UI
@Composable
fun LoginScreen(navController : NavHostController, appState : AppState) {

    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { focusManager.clearFocus() }
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Login",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.DarkGray)
            // Agregamos un Spacer para establecer una distancia entre 'Login' y los campos de entrada
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = {Text("Usuario")},
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
            Spacer(Modifier.height(15.dp))
            Button(
                onClick = {
                    error = iniciarSesionValidacion(usuario, password, appState)
                    if(error==""){
                        navController.navigate("menu")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                )
            ) { 
                Text("Iniciar Sesión",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
            Spacer(Modifier.height(10.dp))
            TextButton(
                onClick = { navController.navigate("registro")},
                modifier = Modifier.align(Alignment.CenterHorizontally)){
                Text("¿No tienes cuenta? Registrate aquí",
                    color = Color.Blue)
            }
        }
    }
}
