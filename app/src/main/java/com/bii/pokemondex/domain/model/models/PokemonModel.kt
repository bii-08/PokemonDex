package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonModel(
    @SerialName("abilities")
    val abilities: List<AbilityModel>? = listOf(),
    @SerialName("base_experience")
    val baseExperience: Int? = 0,
    @SerialName("cries")
    val cries: CriesModel? = CriesModel(),
    @SerialName("forms")
    val forms: List<FormModel>? = listOf(),
    @SerialName("game_indices")
    val gameIndices: List<GameIndiceModel>? = listOf(),
    @SerialName("height")
    val height: Int? = 0,
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("is_default")
    val isDefault: Boolean? = false,
    @SerialName("location_area_encounters")
    val locationAreaEncounters: String? = "",
    @SerialName("moves")
    val moves: List<MoveModel>? = listOf(),
    @SerialName("name")
    val name: String? = "",
    @SerialName("order")
    val order: Int? = 0,
    @SerialName("past_abilities")
    val pastAbilities: List<String>? = listOf(),
    @SerialName("past_types")
    val pastTypes: List<String>? = listOf(),
    @SerialName("species")
    val species: SpeciesModel? = SpeciesModel(),
    @SerialName("sprites")
    val sprites: SpritesModel? = SpritesModel(),
    @SerialName("stats")
    val stats: List<StatModel> = listOf(),
    @SerialName("types")
    val types: List<TypeModel>? = listOf(),
    @SerialName("weight")
    val weight: Int? = 0
)