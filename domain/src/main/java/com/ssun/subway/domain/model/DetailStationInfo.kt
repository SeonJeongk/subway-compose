package com.ssun.subway.domain.model

data class DetailStationInfo(
    val usageDate: String,
    val lineName: String,
    val stationName: String,
    val getOnCount: Long,
    val getOffCount: Long,
)
