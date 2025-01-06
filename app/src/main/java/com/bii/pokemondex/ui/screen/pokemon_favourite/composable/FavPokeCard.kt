package com.bii.pokemondex.ui.screen.pokemon_favourite.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.BitmapImage
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.bii.pokemondex.data.local.entity.FavouritePokemonEntity
import com.bii.pokemondex.ui.screen.pokemon_list.viewModel.PokeColor
import com.bii.pokemondex.ui.theme.RobotoCondensed
import timber.log.Timber

@Composable
fun FavPokeCard(
    pokemon: FavouritePokemonEntity,
    onClick: () -> Unit // Testing
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor
                    )
                )
            )
            .clickable {
            //TODO:
                onClick() // Testing
            }
    ) {

        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.imageUrl)
                    .build(),
                contentDescription = pokemon.name,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                onSuccess = {
                    val image = it.result.image as BitmapImage
                    val bitmap = image.bitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, true)
                    PokeColor.calcDominantColor(bitmap) {
                        dominantColor = it
                    }
                    Timber.tag("Color").e("Color: ${dominantColor.value}")
                },
                onError = {
                    Timber.tag("AsyncImageError").e("Failed to extract color ${it.result.throwable}")
                }
            )

            Text(
                text = pokemon.name,
                fontFamily = RobotoCondensed,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}