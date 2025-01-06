package com.bii.pokemondex.domain.repository

import com.bii.pokemondex.domain.model.models.PokemonModel
import com.bii.pokemondex.domain.model.response.PokemonListModel
import com.bii.pokemondex.ui.util.ApiResponse

interface ApiRepository {
    suspend fun getAllPokemons(limit: Int, offset: Int): ApiResponse<PokemonListModel>
    suspend fun getPokemonByName(name: String): ApiResponse<PokemonModel>
}