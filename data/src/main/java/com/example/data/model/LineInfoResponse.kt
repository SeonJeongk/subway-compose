package com.example.data.model

import com.example.domain.model.HomeLineInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LineInfoResponse(
    @SerialName("SearchSTNBySubwayLineInfo") val searchResult: LineSearchResult,
)

@Serializable
data class LineSearchResult(
    @SerialName("list_total_count") val totalCount: Int,
    @SerialName("RESULT") val result: Result,
    val row: List<StationInfo>,
)

@Serializable
data class StationInfo(
    @SerialName("STATION_CD") val stationCode: String,
    @SerialName("STATION_NM") val stationName: String,
    @SerialName("STATION_NM_ENG") val stationNameEng: String,
    @SerialName("LINE_NUM") val lineNumber: String,
    @SerialName("FR_CODE") val frCode: String,
    @SerialName("STATION_NM_CHN") val stationNameChn: String,
    @SerialName("STATION_NM_JPN") val stationNameJpn: String,
)

fun LineInfoResponse.toDomain(): List<HomeLineInfo> {
    return searchResult.row.map { result ->
        HomeLineInfo(
            lineNumber = result.lineNumber,
            stationName = result.stationName,
            stationNameEng = result.stationNameEng,
            stationNameChn = result.stationNameChn,
            stationNameJpn = result.stationNameJpn
        )
    }
}
