package com.bii.pokemondex.ui.screen.pokemon_details.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FavouriteToggleButton(
    isFavourite: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
        IconToggleButton(
            checked = isFavourite,
            onCheckedChange = onToggle,
            modifier = modifier
        ) {
            Icon(
                imageVector = if (isFavourite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Filled.FavoriteBorder
                },
                contentDescription = null,
                modifier = Modifier
                    .clickable { onToggle(!isFavourite) }
                    .size(40.dp),
                tint = Color.Yellow
            )
        }
}