package com.bii.pokemondex.domain.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultModel(
    @SerialName("name")
    val name: String = "",
    @SerialName("url")
    val url: String = ""
)

