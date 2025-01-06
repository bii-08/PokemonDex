package com.bii.pokemondex.ui.screen.pokemon_favourite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bii.pokemondex.ui.screen.pokemon_favourite.composable.FavPokeCard
import com.bii.pokemondex.ui.screen.pokemon_favourite.viewModel.FavouritePokemonListVM
import com.bii.pokemondex.ui.util.PokemonDetails
import timber.log.Timber


@Composable
fun PokemonFavListView(
    viewModel: FavouritePokemonListVM = hiltViewModel(),
    navController: NavController // Testing
) {
    val favouritePokemons by viewModel.favPokemons.collectAsState()
    Timber.tag("FavouriteTab").e("Number of favourites: ${favouritePokemons.size}")

    if (favouritePokemons.isEmpty()) {
        Text(text = "No items")
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(favouritePokemons) { pokemon ->
                FavPokeCard(
                    pokemon = pokemon,
                    onClick = { // Testing
                        navController.navigate(PokemonDetails(pokemon.name, pokemon.number))
                    }
                )
            }
        }
    }
}
