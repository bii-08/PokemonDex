package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatModel(
    @SerialName("base_stat")
    val baseStat: Int = 0,
    @SerialName("effort")
    val effort: Int = 0,
    @SerialName("stat")
    val stat: StatModelX = StatModelX()
)