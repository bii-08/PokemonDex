package com.bii.pokemondex.ui.screen.pokemon_favourite.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bii.pokemondex.data.local.entity.FavouritePokemonEntity
import com.bii.pokemondex.data.repository.FavoritePokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritePokemonListVM @Inject constructor(
    private val favRepository: FavoritePokemonRepository
): ViewModel() {
    val favPokemons = favRepository.favouritePokemons.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun isPokemonFavorite(pokemonId: Int): Flow<Boolean> {
        return favRepository.isPokemonFavourite(pokemonId)
    }

    fun toggleFavourite(pokemon: FavouritePokemonEntity, isFavourite: Boolean) {
        viewModelScope.launch {
            if (isFavourite) {
                favRepository.addFavourite(pokemon)
            } else {
                favRepository.removeFavourite(pokemon.id)
            }
        }
    }
}