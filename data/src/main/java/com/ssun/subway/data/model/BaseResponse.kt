package com.ssun.subway.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("CODE") val code: String,
    @SerialName("MESSAGE") val message: String,
)
