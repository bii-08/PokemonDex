package com.bii.pokemondex.ui.util

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data object PokemonList

@Serializable
data class PokemonDetails(
    val pokemonName: String,
    val number: Int
)

@Serializable
data object Favourite

