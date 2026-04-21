package com.ssun.subway.data.repository

import com.ssun.subway.data.model.toDomain
import com.ssun.subway.data.service.SubwayService
import com.ssun.subway.domain.model.DetailStationInfo
import com.ssun.subway.domain.model.HomeLineInfo
import com.ssun.subway.domain.repository.MainRepository
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
