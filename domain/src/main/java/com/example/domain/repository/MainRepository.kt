package com.example.domain.repository

import com.example.domain.repository.model.LineInfoResponse
import com.example.domain.repository.model.StationDetailResponse

interface MainRepository {
    suspend fun getAllLineInfo(): Result<LineInfoResponse>
    suspend fun getStationInfo(): Result<StationDetailResponse>
}
