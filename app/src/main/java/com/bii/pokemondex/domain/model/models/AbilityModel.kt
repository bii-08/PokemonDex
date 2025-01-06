package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbilityModel(
    @SerialName("ability")
    val ability: AbilityModelX? = AbilityModelX(),
    @SerialName("is_hidden")
    val isHidden: Boolean? = false,
    @SerialName("slot")
    val slot: Int? = 0
)