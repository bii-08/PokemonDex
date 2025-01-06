package com.bii.pokemondex.domain.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.bii.pokemondex.domain.model.models.PokemonModel
@Serializable
data class PokemonListModel(
    @SerialName("count")
    val count: Int? = 0,
    @SerialName("next")
    val next: String? = "",
    @SerialName("results")
    val results: List<ResultModel>? = listOf()
)

fun List<ResultModel>.toPokemonModelList(): List<PokemonModel> =
    this.map { result ->
        PokemonModel(name = result.name)
    }
