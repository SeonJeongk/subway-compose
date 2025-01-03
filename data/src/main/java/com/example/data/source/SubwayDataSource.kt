package com.example.data.source

import com.example.data.model.LineInfoResponse
import com.example.data.model.StationDetailResponse
import com.example.data.service.SubwayService
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
