package com.bii.pokemondex.ui.screen.pokemon_list.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bii.pokemondex.ui.screen.pokemon_list.PokemonListState


@Composable
fun SearchBar(
    state: PokemonListState,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = state.searchText,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = "Search for pokemon")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null
            )
        },
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.colors(Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 10.dp)
    )
}