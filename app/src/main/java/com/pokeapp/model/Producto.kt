package com.pokeapp.model

import androidx.annotation.DrawableRes
import com.pokeapp.R
data class Product(
  val nombre: String,
  val precio: Double,
  @DrawableRes val imagen: Int
)

// Creación de lista de productos, simulando un localStorage (creo(?))
val productList = listOf(
  Product("Poké ball", 2.0, R.drawable.prod01pokeball),
  Product("Super Ball", 4.0, R.drawable.prod02superball),
  Product("Ultra Ball", 8.0, R.drawable.prod03ultraball),
  Product("Master Ball", 100.0, R.drawable.prod04masterball),
  Product("MT 01", 50.0, R.drawable.prod05mt00),
  Product("MT 02", 50.0, R.drawable.prod06mt01),
  Product("Poción", 2.5, R.drawable.prod07potion),
  Product("Súper Poción", 5.0, R.drawable.prod08superpotion),
  Product("Hyper Poción", 7.5, R.drawable.prod09hiperpotion),
  Product("Revivir", 20.00, R.drawable.prod10revive)
)