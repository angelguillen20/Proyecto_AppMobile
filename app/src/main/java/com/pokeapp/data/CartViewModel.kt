package com.pokeapp.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.pokeapp.model.CartItem
import com.pokeapp.model.Product

class CartViewModel : ViewModel() {

    // 🔹 Lista observable del carrito
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    // 🔹 Agregar producto (permite definir cuántas unidades)
    fun addProduct(productToAdd: Product, cantidad: Int = 1) {
        val existingItem = _cartItems.find { it.product.nombre == productToAdd.nombre }
        if (existingItem != null) {
            val index = _cartItems.indexOf(existingItem)
            if (index != -1) {
                _cartItems[index] = existingItem.copy(quantity = existingItem.quantity + cantidad)
            }
        } else {
            _cartItems.add(CartItem(productToAdd, quantity = cantidad))
        }
    }

    // 🔹 Aumentar cantidad en el carrito
    fun increaseQuantity(cartItem: CartItem) {
        val index = _cartItems.indexOf(cartItem)
        if (index != -1) {
            val current = _cartItems[index]
            _cartItems[index] = current.copy(quantity = current.quantity + 1)
        }
    }

    // 🔹 Disminuir cantidad (si llega a 0, elimina el producto)
    fun decreaseQuantity(cartItem: CartItem) {
        val index = _cartItems.indexOf(cartItem)
        if (index != -1) {
            val current = _cartItems[index]
            if (current.quantity > 1) {
                _cartItems[index] = current.copy(quantity = current.quantity - 1)
            } else {
                _cartItems.removeAt(index)
            }
        }
    }

    // 🔹 Eliminar producto completamente
    fun removeProduct(cartItem: CartItem) {
        _cartItems.remove(cartItem)
    }

    // 🔹 Vaciar el carrito
    fun clearCart() {
        _cartItems.clear()
    }

    // 🔹 Calcular el total
    fun getTotal(): Double {
        return _cartItems.sumOf { it.product.precio * it.quantity }
    }
}

