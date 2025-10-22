package com.pokeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.pokeapp.data.AppState
import com.pokeapp.data.DataStoreManager
import com.pokeapp.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataStore = DataStoreManager(applicationContext)
        val appState = AppState(dataStore)
        setContent {
            myApp(appState)
        }
    }
}

@Composable
fun myApp(appState: AppState){
    val navController = rememberNavController()
    val startDestination = "login"

    MaterialTheme {
        AppNavigation(navController, appState)
    }
}
