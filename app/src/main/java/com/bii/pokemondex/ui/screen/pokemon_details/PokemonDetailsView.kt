package com.bii.pokemondex.ui.screen.pokemon_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.toRoute
import coil3.BitmapImage
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.bii.pokemondex.data.local.entity.FavouritePokemonEntity
import com.bii.pokemondex.domain.model.models.PokemonModel
import com.bii.pokemondex.ui.screen.pokemon_details.composable.FavouriteToggleButton
import com.bii.pokemondex.ui.screen.pokemon_details.composable.PokemonBaseStats
import com.bii.pokemondex.ui.screen.pokemon_details.composable.PokemonDetailDataSection
import com.bii.pokemondex.ui.screen.pokemon_details.composable.PokemonDetailTopSection
import com.bii.pokemondex.ui.screen.pokemon_details.composable.PokemonTypeSection
import com.bii.pokemondex.ui.screen.pokemon_details.viewModel.PokemonDetailsVM
import com.bii.pokemondex.ui.screen.pokemon_favourite.viewModel.FavouritePokemonListVM
import com.bii.pokemondex.ui.screen.pokemon_list.viewModel.PokeColor
import com.bii.pokemondex.ui.util.PokemonDetails
import timber.log.Timber
import java.util.Locale

@Composable
fun PokemonDetailsView(
    pokemon: PokemonModel,
    viewModel: PokemonDetailsVM = hiltViewModel(),
    favViewModel: FavouritePokemonListVM = hiltViewModel(),
    navigate: NavController
) {
    val args = viewModel.savedStateHandle.toRoute<PokemonDetails>()
    val number = args.number ?: throw IllegalArgumentException("Number is required")
    Timber.tag("PokemonDetails").e("Args number: ${args.number} or $number")
    val url =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${args.number}.png"
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    val scrollState = rememberScrollState()
    val isFavorite = remember { mutableStateOf(false) }

    // Testing
    val isFavoriteState by favViewModel.isPokemonFavorite(pokemon.id ?: 0)
        .collectAsState(initial = false) // Testing
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(dominantColor)
            .padding(bottom = 16.dp)
    ) {

        PokemonDetailTopSection(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .align(Alignment.TopStart),
            dominantColor = dominantColor,
            navigate = navigate
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            FavouriteToggleButton(
                // Testing
                isFavourite = isFavoriteState,
                onToggle = { newState ->
                    isFavorite.value = newState
                    val favouriteEntity = FavouritePokemonEntity(
                        id = pokemon.id ?: 0,
                        name = pokemon.name ?: "",
                        imageUrl = url,
                        number = number  // Testing
                    )
                    favViewModel.toggleFavourite(favouriteEntity, newState)
                },

                modifier = Modifier
                    .size(40.dp)
            )
        }

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 20.dp + 200.dp / 2f,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 100.dp)
                    .verticalScroll(scrollState)
            ) {
                Text(
                    text = "#${args.number} ${
                        args.pokemonName.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                locale = Locale.ROOT
                            ) else it.toString()
                        }
                    }",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )

                PokemonTypeSection(types = pokemon.types ?: listOf())
                PokemonDetailDataSection(
                    pokemonWeight = pokemon.weight ?: 0,
                    pokemonHeight = pokemon.height ?: 0
                )
                PokemonBaseStats(pokemon = pokemon)
            }
        }

        if (viewModel.pokemon.value != null) {
            AsyncImage(
                ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .build(),
                contentDescription = pokemon.name,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(200.dp)
                    .offset(y = 20.dp),
                onSuccess = {
                    val image = it.result.image as BitmapImage
                    val bitmap = image.bitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, true)
                    PokeColor.calcDominantColor(bitmap) {
                        dominantColor = it
                    }
                }
            )
        }
    }
}

