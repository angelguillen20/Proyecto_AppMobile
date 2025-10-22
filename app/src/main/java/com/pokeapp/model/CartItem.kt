package com.pokeapp.model

data class CartItem(
    val product: Product,
    var quantity: Int = 1 // La cantidad de este producto
)