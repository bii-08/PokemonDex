package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationVModel(
    @SerialName("black-white")
    val blackWhite: BlackWhiteModel? = BlackWhiteModel()
)