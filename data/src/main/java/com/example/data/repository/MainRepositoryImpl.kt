package com.example.data.repository

import com.example.data.service.SubwayService
import com.example.domain.repository.MainRepository
import com.example.domain.repository.model.LineInfoResponse
import com.example.domain.repository.model.StationDetailResponse
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val subwayService: SubwayService
) : MainRepository {
    override suspend fun getAllLineInfo(): Result<LineInfoResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getStationInfo(): Result<StationDetailResponse> {
        TODO("Not yet implemented")
    }

}
