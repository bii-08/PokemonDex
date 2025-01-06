package com.bii.pokemondex.data.repository

import com.bii.pokemondex.data.local.dao.FavouritePokemonDao
import com.bii.pokemondex.data.local.entity.FavouritePokemonEntity
import kotlinx.coroutines.flow.Flow

class FavoritePokemonRepository(private val dao: FavouritePokemonDao) {
    val favouritePokemons: Flow<List<FavouritePokemonEntity>> = dao.getAllFavouritePokemons()

    suspend fun addFavourite(pokemon: FavouritePokemonEntity) {
        dao.addFavouritePokemon(pokemon)
    }

    suspend fun removeFavourite(pokemonId: Int) {
        dao.removeFavouritePokemon(pokemonId)
    }

    // Testing
    fun isPokemonFavourite(pokemonId: Int): Flow<Boolean> {
        return dao.isPokemonFavourite(pokemonId)
    }
}