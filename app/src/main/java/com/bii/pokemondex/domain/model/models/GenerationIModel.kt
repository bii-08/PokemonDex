package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationIModel(
    @SerialName("red-blue")
    val redBlue: RedBlueModel? = RedBlueModel(),
    @SerialName("yellow")
    val yellow: YellowModel? = YellowModel()
)