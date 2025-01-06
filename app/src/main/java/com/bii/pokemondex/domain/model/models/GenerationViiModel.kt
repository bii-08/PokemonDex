package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationViiModel(
    @SerialName("icons")
    val icons: IconsModel? = IconsModel(),
    @SerialName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoonModel? = UltraSunUltraMoonModel()
)