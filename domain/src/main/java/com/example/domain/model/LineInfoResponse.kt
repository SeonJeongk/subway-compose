package com.example.domain.model

import kotlinx.serialization.SerialName

data class LineInfoResponse(
    @SerialName("list_total_count")val totalCount: Int,
    @SerialName("RESULT") val result: List<Result>,
    val row: List<StationInfo>,
)

data class StationInfo(
    @SerialName("STATION_CD") val stationCode: String,
    @SerialName("STATION_NM") val stationName: String,
    @SerialName("STATION_NM_ENG") val stationNameEng: String,
    @SerialName("LINE_NUM") val lineNumber: String,
    @SerialName("FR_CODE") val frCode: String,
    @SerialName("STATION_NM_CHN") val stationNameChn: String,
    @SerialName("STATION_NM_JPN") val stationNameJpn: String
)
