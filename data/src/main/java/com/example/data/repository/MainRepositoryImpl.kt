package com.example.data.repository

import com.example.data.model.toDomain
import com.example.data.service.SubwayService
import com.example.domain.model.DetailStationInfo
import com.example.domain.model.HomeLineInfo
import com.example.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val subwayService: SubwayService,
) : MainRepository {
    override suspend fun getAllLineInfo(): Result<List<HomeLineInfo>> {
        return runCatching {
            subwayService.getAllLineInfo().toDomain()
        }
    }

    override suspend fun getStationInfo(
        date: String,
        line: String,
    ): Result<List<DetailStationInfo>> {
        return runCatching {
            subwayService.getStationInfo(date, line).toDomain()
        }
    }

}
