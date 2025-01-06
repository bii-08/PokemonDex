package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationIvModel(
    @SerialName("diamond-pearl")
    val diamondPearl: DiamondPearlModel? = DiamondPearlModel(),
    @SerialName("heartgold-soulsilver")
    val heartgoldSoulsilver: HeartgoldSoulsilverModel? = HeartgoldSoulsilverModel(),
    @SerialName("platinum")
    val platinum: PlatinumModel? = PlatinumModel()
)