package com.bii.pokemondex.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bii.pokemondex.data.local.entity.FavouritePokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritePokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavouritePokemon(pokemon: FavouritePokemonEntity)

    @Query("DELETE FROM favourite_pokemon WHERE id = :pokemonId")
    suspend fun removeFavouritePokemon(pokemonId: Int)

    @Query("SELECT * FROM favourite_pokemon")
    fun getAllFavouritePokemons(): Flow<List<FavouritePokemonEntity>>

    // Testing
    @Query("SELECT EXISTS(SELECT 1 FROM favourite_pokemon WHERE id = :pokemonId)")
    fun isPokemonFavourite(pokemonId: Int): Flow<Boolean>

}