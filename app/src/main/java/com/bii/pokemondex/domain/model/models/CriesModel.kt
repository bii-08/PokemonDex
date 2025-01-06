package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CriesModel(
    @SerialName("latest")
    val latest: String? = "",
    @SerialName("legacy")
    val legacy: String? = ""
)