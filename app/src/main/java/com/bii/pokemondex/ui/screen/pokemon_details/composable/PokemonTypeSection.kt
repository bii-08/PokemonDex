package com.bii.pokemondex.ui.screen.pokemon_details.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bii.pokemondex.domain.model.models.TypeModel
import com.bii.pokemondex.ui.util.parseTypeToColor

@Composable
fun PokemonTypeSection(types: List<TypeModel>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
    )
    {
        for (type in types) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .clip(CircleShape)
                    .background(parseTypeToColor(type))
                    .height(35.dp)
            ) {
                Text(
                    text = type.type?.name?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            java.util.Locale.ROOT
                        ) else it.toString()
                    }
                        ?: "",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}