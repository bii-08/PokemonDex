package com.bii.pokemondex.ui.screen.pokemon_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bii.pokemondex.R
import com.bii.pokemondex.domain.model.response.PokedexListEntry
import com.bii.pokemondex.ui.screen.pokemon_list.composable.PokeCard
import com.bii.pokemondex.ui.screen.pokemon_list.composable.RetryView
import com.bii.pokemondex.ui.screen.pokemon_list.composable.SearchBar
import com.bii.pokemondex.ui.screen.pokemon_list.viewModel.PokemonListVM

@Composable
fun PokemonListView(
    state: PokemonListState,
    onValueChange: (String) -> Unit,
    navigate: (PokedexListEntry) -> Unit,
    viewModel: PokemonListVM = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            SearchBar(state = state, onValueChange = onValueChange)

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxSize()
            )  {
                val pokemons = state.filteredPokemons.ifEmpty { state.pokemons }
                // Display Pokemon items
                items(pokemons.size) { index ->
                    PokeCard(
                        entry = pokemons[index],
                        onClick = { entry ->
                            navigate(entry)
                        }
                    )
                }

                item {
                    if (state.pokemons.isNotEmpty()) {
                        LaunchedEffect(Unit) {
                            viewModel.fetchPokemons()
                        }
                    }
                }
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (viewModel.isError.value != "") {
                RetryView(
                    error = viewModel.isError.value
                ) {
                    viewModel.fetchPokemons()
                }
            }
        }
    }
}
