package com.ssun.subway.data.model

import com.ssun.subway.domain.model.DetailStationInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StationDetailResponse(
    @SerialName("CardSubwayStatsNew") val searchResult: StationSearchResult? = null,
    @SerialName("RESULT") val result: Result? = null,
)

@Serializable
data class StationSearchResult(
    @SerialName("list_total_count") val totalCount: Int = 0,
    @SerialName("RESULT") val result: Result? = null,
    val row: List<StationsInfo> = emptyList(),
)

@Serializable
data class StationsInfo(
    @SerialName("USE_YMD") val usageDate: String,
    @SerialName("SBWY_ROUT_LN_NM") val lineName: String,
    @SerialName("SBWY_STNS_NM") val stationName: String,
    @SerialName("GTON_TNOPE") val getOnCount: String,
    @SerialName("GTOFF_TNOPE") val getOffCount: String,
    @SerialName("REG_YMD") val registrationDate: String,
)

fun StationDetailResponse.toDomain(): List<DetailStationInfo> {
    return searchResult?.row?.map { result ->
        DetailStationInfo(
            usageDate = result.usageDate,
            lineName = result.lineName,
            stationName = result.stationName,
            getOnCount = result.getOnCount.toLongOrNull() ?: 0L,
            getOffCount = result.getOffCount.toLongOrNull() ?: 0L,
        )
    }.orEmpty()
}
