package com.example.data.repository

import com.example.data.service.SubwayService
import com.example.domain.model.LineInfoResponse
import com.example.domain.model.StationDetailResponse
import com.example.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val subwayService: SubwayService,
) : MainRepository {
    override suspend fun getAllLineInfo(): Result<LineInfoResponse> {
        return runCatching {
            subwayService.getAllLineInfo()
        }.fold(
            onSuccess = { response ->
                Result.success(response)
            },
            onFailure = { exception ->
                Result.failure(exception)
            }
        )
    }

    override suspend fun getStationInfo(date: String, line: String): Result<StationDetailResponse> {
        return runCatching {
            subwayService.getStationInfo(date, line)
        }.fold(
            onSuccess = { response ->
                Result.success(response)
            },
            onFailure = { exception ->
                Result.failure(exception)
            }
        )
    }

}
