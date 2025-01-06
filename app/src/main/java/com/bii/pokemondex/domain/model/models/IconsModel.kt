package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IconsModel(
    @SerialName("front_default")
    val frontDefault: String? = "",
    @SerialName("front_female")
    val frontFemale: String? = "" //
)