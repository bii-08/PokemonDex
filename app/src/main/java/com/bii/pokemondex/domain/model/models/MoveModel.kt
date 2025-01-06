package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoveModel(
    @SerialName("move")
    val move: MoveModelX? = MoveModelX(),
    @SerialName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetailModel>? = listOf()
)