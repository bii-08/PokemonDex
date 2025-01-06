package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionGroupDetailModel(
    @SerialName("level_learned_at")
    val levelLearnedAt: Int? = 0,
    @SerialName("move_learn_method")
    val moveLearnMethod: MoveLearnMethodModel? = MoveLearnMethodModel(),
    @SerialName("version_group")
    val versionGroup: VersionGroupModel? = VersionGroupModel()
)