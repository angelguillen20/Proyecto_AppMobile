package com.pokeapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.pokeapp.ui.theme.pkmnFont
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.pokeapp.data.AppState
import com.pokeapp.R
import com.pokeapp.data.CartViewModel
import com.pokeapp.model.Product
import com.pokeapp.model.productList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavHostController, appState: AppState, viewModel: CartViewModel) {
    var selectedItem by remember { mutableStateOf(0) }
    val navItems = listOf("Inicio", "Categorías", "Perfil")
    val cartItemCount = viewModel.cartItems.size

    Scaffold(
        // Elaboración de la barra superior
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("PokéShop", fontFamily = pkmnFont) },
                actions = {
                    IconButton(onClick = {navController.navigate("qr")}) {
                        Icon(imageVector = Icons.Default.QrCodeScanner,
                            contentDescription = "Escanear QR")
                    }
                    IconButton(onClick = { /* Elaborar [búsqueda] a futuro */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                    IconButton(onClick = {navController.navigate("Carrito")}) {
                        BadgedBox(
                            badge = {
                                if (cartItemCount > 0) {
                                    Badge { Text(cartItemCount.toString()) }
                                }
                            }
                        ) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            when (item) {
                                "Inicio" -> Icon(Icons.Default.Home, contentDescription = item)
                                "Categorías" -> Icon(Icons.Outlined.Category, contentDescription = item)
                                "Perfil" -> Icon(Icons.Default.Person, contentDescription = item)
                            }
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = {
                            if (selectedItem==0){
                                navController.navigate("home")
                            }
                            // Cuando se elaboré la página de categorías y perfil se implementarán en esta línea de código
                            // TIP: Podríamos ver si es que la bottomBar y topBar se puede trabajar como un component de React
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Apartado visual, estructura de la página Home
            item(span = { GridItemSpan(2) }) { Banner() }
            item(span = { GridItemSpan(2) }) { Spacer(modifier = Modifier.height(8.dp)) }
            item(span = { GridItemSpan(2) }) { CategoryCarousel() }
            item(span = { GridItemSpan(2) }) { Spacer(modifier = Modifier.height(8.dp)) }
            item(span = { GridItemSpan(2) }) { Text("Recomendado para ti", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold) }
            
            // Creámos la cuadrícula de productos con la lista de productos importada de model>Producto.kt
            items(productList) { product ->
                ProductCard(product = product, viewModel = viewModel)
            }
        }
    }
}

@Composable
// Elaboración del banner
fun Banner() {
    // Apartado visual
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        // Imagen presente en el banner
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.promo),
                contentDescription = "Banner de promoción",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
// Elaboración de la sección de categorías que aparece debajo del banner
fun CategoryCarousel() {
    val categories = listOf("Novedades", "PokéBalls", "Mt's", "Medicina")
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(categories) {
            ElevatedSuggestionChip(onClick = {}, label = { Text(it) })
        }
    }
}

@Composable
// Elaboración de la tarjeta de producto
fun ProductCard(product: Product, modifier: Modifier = Modifier,viewModel: CartViewModel) {
    Card(
        // Modificación visual de la tarjeta
        modifier = modifier.height(250.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        // Imagen presente en la tarjeta
        // IMPORTANE: Falta agregar onClick() que permita insertar objeto al carrito de compra
        Column {
            Image(
                painter = painterResource(id = product.imagen),
                contentDescription = "Imagen de ${product.nombre}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
            // No me sale el poner el texto en el centro, lo veré mañana jaja
            Column(Modifier.padding(6.dp),horizontalAlignment = Alignment.CenterHorizontally)
            {
                Text(product.nombre, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, maxLines = 1)
                Text("$${product.precio}", style = MaterialTheme.typography.bodyMedium)
                Button(
                    onClick = {viewModel.addProduct(product)},
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    Text("Agregar a Carrito")
                }
            }
        }
    }
}