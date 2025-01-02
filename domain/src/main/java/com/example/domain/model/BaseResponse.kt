package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Result (
    val CODE: String,
    val MESSAGE: String
)
