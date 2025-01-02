package com.example.domain.repository

import com.example.domain.model.LineInfoResponse
import com.example.domain.model.StationDetailResponse

interface MainRepository {
    suspend fun getAllLineInfo(): Result<LineInfoResponse>
    suspend fun getStationInfo(date: String, line: String): Result<StationDetailResponse>
}
