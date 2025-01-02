package com.example.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StationDetailResponse(
    @SerialName("CardSubwayStatsNew") val searchResult: StationSearchResult,
)

@Serializable
data class StationSearchResult(
    @SerialName("list_total_count") val totalCount: Int? = null,
    @SerialName("RESULT") val result: Result,
    val row: List<StationsInfo>? = null
)

@Serializable
data class StationsInfo(
    @SerialName("USE_YMD") val usageDate: String,
    @SerialName("SBWY_ROUT_LN_NM") val lineName: String,
    @SerialName("SBWY_STNS_NM") val stationName: String,
    @SerialName("GTON_TNOPE") val getOnCount: Double,
    @SerialName("GTOFF_TNOPE") val getOffCount: Double,
    @SerialName("REG_YMD") val registrationDate: String,
)
