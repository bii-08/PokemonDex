package com.bii.pokemondex.ui.screen.pokemon_list

import com.bii.pokemondex.domain.model.response.PokedexListEntry

data class PokemonListState (
    val searchText: String = "",
    val pokemons: List<PokedexListEntry> = emptyList(),
    val filteredPokemons: List<PokedexListEntry> = emptyList()
)