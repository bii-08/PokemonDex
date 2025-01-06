package com.bii.pokemondex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bii.pokemondex.ui.screen.pokemon_details.viewModel.PokemonDetailsVM
import com.bii.pokemondex.ui.screen.pokemon_details.PokemonDetailsView
import com.bii.pokemondex.ui.screen.pokemon_favourite.viewModel.FavouritePokemonListVM
import com.bii.pokemondex.ui.screen.pokemon_favourite.PokemonFavListView
import com.bii.pokemondex.ui.screen.pokemon_list.viewModel.PokemonListVM
import com.bii.pokemondex.ui.screen.pokemon_list.PokemonListView
import com.bii.pokemondex.ui.theme.JetpackComposePokedexTheme
import com.bii.pokemondex.ui.util.Favourite
import com.bii.pokemondex.ui.util.PokemonDetails
import com.bii.pokemondex.ui.util.PokemonList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposePokedexTheme {
               val navController = rememberNavController()
                val items = listOf(
                    BottomNavigationItem(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home
                    ),
                    BottomNavigationItem(
                        title = "Favourite",
                        selectedIcon = Icons.Filled.Favorite,
                        unselectedIcon = Icons.Outlined.FavoriteBorder
                    )
                )
                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }
                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            modifier = Modifier
                                .graphicsLayer {
                                    shape = RoundedCornerShape(
                                        topStart = 15.dp,
                                        topEnd = 15.dp
                                    )
                                    clip = true
                                }
                        ) {
                            items.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    selected = selectedItemIndex == index,
                                    onClick = {
                                        selectedItemIndex = index
                                        if (item.title == "Home") {
                                            navController.navigate(PokemonList)
                                        } else {
                                            navController.navigate(Favourite)
                                        }
                                    },
                                    label = {
                                        Text(text = item.title)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if(index == selectedItemIndex) { item.selectedIcon } else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = androidx.compose.ui.Modifier.padding(innerPadding)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = PokemonList
                        ) {

                            composable<PokemonList> {
                                val viewModel = hiltViewModel<PokemonListVM>()
                                val state by viewModel.state.collectAsStateWithLifecycle()
                                PokemonListView(
                                    state = state,
                                    onValueChange = viewModel::onSearchTextChange,
                                    navigate = { pokedex ->
                                        navController.navigate(PokemonDetails(pokedex.pokemonName, pokedex.number)
                                        )
                                    }
                                )
                            }

                            composable<PokemonDetails> {
                                val viewModel = hiltViewModel<PokemonDetailsVM>()
                                viewModel.pokemon.value?.let {
                                    PokemonDetailsView(
                                        pokemon = it,
                                        navigate = navController
                                    )
                                }
                            }

                            composable<Favourite> {
                                val viewModel = hiltViewModel<FavouritePokemonListVM>()
                                PokemonFavListView(
                                    viewModel = viewModel,
                                    navController = navController // Testing
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposePokedexTheme {

    }
}