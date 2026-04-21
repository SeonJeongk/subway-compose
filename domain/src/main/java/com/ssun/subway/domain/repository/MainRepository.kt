package com.ssun.subway.domain.repository

import com.ssun.subway.domain.model.DetailStationInfo
import com.ssun.subway.domain.model.HomeLineInfo

interface MainRepository {
    suspend fun getAllLineInfo(): Result<List<HomeLineInfo>>
    suspend fun getStationInfo(date: String, line: String): Result<List<DetailStationInfo>>
}
