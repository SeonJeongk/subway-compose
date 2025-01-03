package com.example.domain.repository

import com.example.domain.model.DetailStationInfo
import com.example.domain.model.HomeLineInfo

interface MainRepository {
    suspend fun getAllLineInfo(): Result<List<HomeLineInfo>>
    suspend fun getStationInfo(date: String, line: String): Result<List<DetailStationInfo>>
}
