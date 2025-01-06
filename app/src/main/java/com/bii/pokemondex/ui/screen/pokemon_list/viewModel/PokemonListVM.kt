package com.bii.pokemondex.ui.screen.pokemon_list.viewModel

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.bii.pokemondex.domain.model.response.PokedexListEntry
import com.bii.pokemondex.domain.repository.ApiRepository
import com.bii.pokemondex.ui.screen.pokemon_list.PokemonListState
import com.bii.pokemondex.ui.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListVM @Inject constructor(
    private val apiRepository: ApiRepository
): ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val pokemonNewList = mutableStateOf<List<PokedexListEntry>>(listOf())
    val state = _state.asStateFlow()
    var isLoading = mutableStateOf(false)
    var isError = mutableStateOf("")
    private var offset = 0
    private val limit = 20

    init {
        fetchPokemons()
    }

    fun fetchPokemons() {
        viewModelScope.launch {
            isLoading.value = true
            when (val response = apiRepository.getAllPokemons(limit, offset)) {
                is ApiResponse.Success -> {

                    val pokedexEntries = response.data?.results?.mapIndexed { index, entry ->
                        val number = if (entry.url.endsWith(suffix = "/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }

                        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png"
                        PokedexListEntry(
                            entry.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                            imageUrl = url,
                            number = number.toInt()
                        )
                    }
                    isLoading.value = false
                    isError.value = ""
                    pokemonNewList.value += pokedexEntries ?: listOf()
                    _state.update {
                        it.copy(pokemons = pokemonNewList.value) // Update your state with the list of Pokemons
                    }
                    offset += limit
                }
                is ApiResponse.Error -> {
                    isLoading.value = false
                    isError.value = response.message ?: ""
                    // Handle error, e.g., show a message
                    _state.update {
                        it.copy("")
                    }
                }

                is ApiResponse.Loading -> TODO()
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _state.update {
            it.copy(
                searchText = text,
                filteredPokemons = it.pokemons.filter { pokemon ->
                    pokemon.pokemonName.contains(text, ignoreCase = true) == true
                }
            )
        }
    }
}

object PokeColor {
    fun calcDominantColor(bitmap: Bitmap, onResult: (Color) -> Unit) {
        Palette.from(bitmap).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onResult(Color(colorValue))
            }
        }
    }
}