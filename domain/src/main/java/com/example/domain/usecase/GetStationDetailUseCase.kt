package com.example.domain.usecase

import com.example.domain.model.DetailStationInfo
import com.example.domain.repository.MainRepository
import javax.inject.Inject

class GetStationDetailUseCase @Inject constructor(
    private val repository: MainRepository,
) {
    suspend operator fun invoke(date: String, line: String): Result<List<DetailStationInfo>> {
        return repository.getStationInfo(date, line)
    }
}
