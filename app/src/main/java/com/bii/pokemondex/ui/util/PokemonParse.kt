package com.bii.pokemondex.ui.util

import com.bii.pokemondex.domain.model.models.StatModel
import com.bii.pokemondex.domain.model.models.TypeModel
import com.bii.pokemondex.ui.theme.AtkColor
import com.bii.pokemondex.ui.theme.DefColor
import com.bii.pokemondex.ui.theme.HPColor
import com.bii.pokemondex.ui.theme.SpAtkColor
import com.bii.pokemondex.ui.theme.SpDefColor
import com.bii.pokemondex.ui.theme.SpdColor
import com.bii.pokemondex.ui.theme.TypeBug
import com.bii.pokemondex.ui.theme.TypeDark
import com.bii.pokemondex.ui.theme.TypeDragon
import com.bii.pokemondex.ui.theme.TypeElectric
import com.bii.pokemondex.ui.theme.TypeFairy
import com.bii.pokemondex.ui.theme.TypeFighting
import com.bii.pokemondex.ui.theme.TypeFire
import com.bii.pokemondex.ui.theme.TypeFlying
import com.bii.pokemondex.ui.theme.TypeGhost
import com.bii.pokemondex.ui.theme.TypeGrass
import com.bii.pokemondex.ui.theme.TypeGround
import com.bii.pokemondex.ui.theme.TypeIce
import com.bii.pokemondex.ui.theme.TypeNormal
import com.bii.pokemondex.ui.theme.TypePoison
import com.bii.pokemondex.ui.theme.TypePsychic
import com.bii.pokemondex.ui.theme.TypeRock
import com.bii.pokemondex.ui.theme.TypeSteel
import com.bii.pokemondex.ui.theme.TypeWater

fun parseTypeToColor(type: TypeModel): androidx.compose.ui.graphics.Color {
    return when(type.type?.name?.lowercase()) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> androidx.compose.ui.graphics.Color.Black
    }
}

fun parseStatToColor(stat: StatModel): androidx.compose.ui.graphics.Color {
    return when(stat.stat?.name?.lowercase()) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> androidx.compose.ui.graphics.Color.White
    }
}

fun parseStatToAbbr(stat: StatModel): String {
    return when(stat.stat?.name?.lowercase()) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}