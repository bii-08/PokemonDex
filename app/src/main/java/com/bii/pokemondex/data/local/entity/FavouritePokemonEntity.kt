package com.bii.pokemondex.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_pokemon")
data class FavouritePokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val number: Int // Testing
)
