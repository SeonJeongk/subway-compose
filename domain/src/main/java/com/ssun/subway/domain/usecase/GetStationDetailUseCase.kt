package com.ssun.subway.domain.usecase

import com.ssun.subway.domain.model.DetailStationInfo
import com.ssun.subway.domain.repository.MainRepository
import javax.inject.Inject

class GetStationDetailUseCase @Inject constructor(
    private val repository: MainRepository,
) {
    suspend operator fun invoke(date: String, line: String): Result<List<DetailStationInfo>> {
        return repository.getStationInfo(date, line)
    }
}
