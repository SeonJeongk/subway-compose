package com.ssun.subway.data.source

import com.ssun.subway.data.model.LineInfoResponse
import com.ssun.subway.data.model.StationDetailResponse
import com.ssun.subway.data.service.SubwayService
import javax.inject.Inject

class SubwayDataSource @Inject constructor(
    private val subwayService: SubwayService,
) {
    suspend fun getAllLineInfo(): LineInfoResponse {
        return subwayService.getAllLineInfo()
    }

    suspend fun getStationInfo(date: String, line: String): StationDetailResponse {
        return subwayService.getStationInfo(date, line)
    }
}
