package com.bii.pokemondex.ui.screen.pokemon_details.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.bii.pokemondex.domain.model.models.PokemonModel
import com.bii.pokemondex.domain.repository.ApiRepository
import com.bii.pokemondex.ui.util.ApiResponse
import com.bii.pokemondex.ui.util.PokemonDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsVM @Inject constructor(
    private val apiRepository: ApiRepository,
    val savedStateHandle: SavedStateHandle
): ViewModel() {
    var pokemon = mutableStateOf<PokemonModel?>(null)

    init {
        val args = savedStateHandle.toRoute<PokemonDetails>()
        viewModelScope.launch {
            when (val response = apiRepository.getPokemonByName(args.pokemonName)) {
                is ApiResponse.Error -> {
                    Log.d("PokemonDetailsVM", "ChampionDetailsViewModel: ${response.message}")
                }
                is ApiResponse.Loading -> {
                    TODO()
                }
                is ApiResponse.Success -> {
                    Log.d("PokemonDetailsVM", "ChampionDetailsViewModel: ${response.data}")
                    pokemon.value = response.data

                }
            }
        } 
    }
}