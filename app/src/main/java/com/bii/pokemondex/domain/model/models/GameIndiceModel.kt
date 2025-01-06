package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameIndiceModel(
    @SerialName("game_index")
    val gameIndex: Int? = 0,
    @SerialName("version")
    val version: VersionModel? = VersionModel()
)