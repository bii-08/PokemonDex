package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationViModel(
    @SerialName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphireModel? = OmegarubyAlphasapphireModel(),
    @SerialName("x-y")
    val xY: XYModel? = XYModel()
)