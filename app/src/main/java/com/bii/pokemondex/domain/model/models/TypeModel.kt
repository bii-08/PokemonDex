package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeModel(
    @SerialName("slot")
    val slot: Int? = 0,
    @SerialName("type")
    val type: TypeModelX? = TypeModelX()
)