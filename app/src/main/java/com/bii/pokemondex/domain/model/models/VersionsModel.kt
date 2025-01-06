package com.bii.pokemondex.domain.model.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionsModel(
    @SerialName("generation-i")
    val generationI: GenerationIModel? = GenerationIModel(),
    @SerialName("generation-ii")
    val generationIi: GenerationIiModel? = GenerationIiModel(),
    @SerialName("generation-iii")
    val generationIii: GenerationIiiModel? = GenerationIiiModel(),
    @SerialName("generation-iv")
    val generationIv: GenerationIvModel? = GenerationIvModel(),
    @SerialName("generation-v")
    val generationV: GenerationVModel? = GenerationVModel(),
    @SerialName("generation-vi")
    val generationVi: GenerationViModel? = GenerationViModel(),
    @SerialName("generation-vii")
    val generationVii: GenerationViiModel? = GenerationViiModel(),
    @SerialName("generation-viii")
    val generationViii: GenerationViiiModel? = GenerationViiiModel()
)