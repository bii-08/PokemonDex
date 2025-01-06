package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OtherModel(
    @SerialName("dream_world")
    val dreamWorld: DreamWorldModel? = DreamWorldModel(),
    @SerialName("home")
    val home: HomeModel? = HomeModel(),
    @SerialName("official-artwork")
    val officialArtwork: OfficialArtworkModel? = OfficialArtworkModel(),
    @SerialName("showdown")
    val showdown: ShowdownModel? = ShowdownModel()
)