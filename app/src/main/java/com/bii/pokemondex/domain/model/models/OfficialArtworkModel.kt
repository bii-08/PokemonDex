package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfficialArtworkModel(
    @SerialName("front_default")
    val frontDefault: String? = "",
    @SerialName("front_shiny")
    val frontShiny: String? = ""
)