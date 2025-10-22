package com.pokeapp.viewimport

import android.widget.Toast

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect // <-- Importante
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun QRScreen(navController: NavController) {
    val context = LocalContext.current

    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            // Este bloque se ejecuta CUANDO la cámara nos devuelve un resultado
            if (result.contents == null) {
                // El usuario cerró la cámara sin escanear (presionó "atrás")
                Toast.makeText(context, "Escaneo cancelado", Toast.LENGTH_SHORT).show()
                // Navegamos hacia atrás para volver a la pantalla anterior (MenuScreen)
                navController.popBackStack()
            } else {
                // ¡Éxito! Tenemos el valor del QR
                val qrValue = result.contents
                Toast.makeText(context, "QR Escaneado: $qrValue", Toast.LENGTH_LONG).show()

                // Aquí puedes hacer lo que quieras con el valor del QR.
                // Por ahora, solo volvemos a la pantalla anterior.
                navController.popBackStack()
            }
        }
    )

    // --- ¡AQUÍ ESTÁ LA MAGIA! ---
    // Este efecto se lanza UNA SOLA VEZ cuando QRScreen aparece.
    LaunchedEffect(key1 = true) {
        // Configuramos y lanzamos la cámara de inmediato.
        val options = ScanOptions()
        options.setPrompt("Escanea un código QR")
        options.setBeepEnabled(true)
        options.setOrientationLocked(false)
        scanLauncher.launch(options)
    }

    // Opcional: Muestra un indicador de carga mientras la cámara se inicia.
    // Esto es lo que el usuario ve por una fracción de segundo.
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Text(text = "Abriendo cámara...")
    }
}
