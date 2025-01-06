package com.bii.pokemondex.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bii.pokemondex.data.local.dao.FavouritePokemonDao
import com.bii.pokemondex.data.local.entity.FavouritePokemonEntity

@Database(
    entities = [FavouritePokemonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favoritePokemonDao(): FavouritePokemonDao
}
