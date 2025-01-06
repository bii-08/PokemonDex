package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationIiiModel(
    @SerialName("emerald")
    val emerald: EmeraldModel? = EmeraldModel(),
    @SerialName("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreenModel? = FireredLeafgreenModel(),
    @SerialName("ruby-sapphire")
    val rubySapphire: RubySapphireModel? = RubySapphireModel()
)