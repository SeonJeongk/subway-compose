package com.example.domain.model

import kotlinx.serialization.SerialName

data class StationDetailResponse (
    @SerialName("list_total_count")val totalCount: Int,
    @SerialName("RESULT") val result: List<Result>,
    val row: List<StationsInfo>
)

data class StationsInfo(
    @SerialName("USE_YMD") val usageDate: String,
    @SerialName("SBWY_ROUT_LN_NM") val lineName: String,
    @SerialName("SBWY_STNS_NM") val stationName: String,
    @SerialName("GTON_TNOPE") val getOnCount: Int,
    @SerialName("GTOFF_TNOPE") val getOffCount: Int,
    @SerialName("REG_YMD") val registrationDate: String
)
