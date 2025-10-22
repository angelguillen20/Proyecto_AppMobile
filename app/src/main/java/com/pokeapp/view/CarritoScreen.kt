package com.pokeapp.view
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pokeapp.data.CartViewModel
import com.pokeapp.model.CartItem
import com.pokeapp.model.Product
import org.w3c.dom.Text

@Composable
fun CarritoScreen(navController: NavHostController, viewModel: CartViewModel = viewModel(), onBack: () -> Unit) {
    val cart = viewModel.cartItems

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 8.dp)
            .padding(top = 8.dp)
    ) {
        Text(text = "🛒 Tu carrito", modifier = Modifier.padding(bottom = 8.dp))

         Button(onClick = onBack)
            { Text("Volver") }
        if (cart.isEmpty()) {
            Text(text = "El carrito está vacío")
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(cart) { cartItem ->
                    CartItem(cartItem, viewModel,onRemove = { viewModel.removeProduct(cartItem) })
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Total: $${viewModel.getTotal()}")
            Button(
                onClick = { navController.navigate("pago")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text("Finalizar compra")
            }
        }
    }
}

@Composable
fun CartItem(cartItem: CartItem,viewModel: CartViewModel , onRemove: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = cartItem.product.imagen),
                contentDescription = cartItem.product.nombre,
                modifier = Modifier.size(64.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            ) {
                Text(text = cartItem.product.nombre)
                Text(text = "$${cartItem.product.precio}")
            }
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){ IconButton(onClick = {viewModel.decreaseQuantity(cartItem)}) {
                Text("-")
            }
                Text(
                    text = "${cartItem.quantity}",
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                IconButton(onClick = {viewModel.increaseQuantity(cartItem)}) {
                    Text("+")
                }
                Text(
                    text = "$${cartItem.product.precio * cartItem.quantity}",
                )
            }

            IconButton(onClick = onRemove) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    }
}

