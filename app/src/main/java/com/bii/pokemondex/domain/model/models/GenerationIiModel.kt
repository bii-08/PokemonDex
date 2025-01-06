package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationIiModel(
    @SerialName("crystal")
    val crystal: CrystalModel? = CrystalModel(),
    @SerialName("gold")
    val gold: GoldModel? = GoldModel(),
    @SerialName("silver")
    val silver: SilverModel? = SilverModel()
)