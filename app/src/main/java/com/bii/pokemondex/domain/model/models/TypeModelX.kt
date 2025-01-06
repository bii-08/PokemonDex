package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeModelX(
    @SerialName("name")
    val name: String? = "",
    @SerialName("url")
    val url: String? = ""
)