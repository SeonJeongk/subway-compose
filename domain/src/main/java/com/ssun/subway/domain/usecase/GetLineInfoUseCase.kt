package com.ssun.subway.domain.usecase

import com.ssun.subway.domain.model.HomeLineInfo
import com.ssun.subway.domain.repository.MainRepository
import javax.inject.Inject

class GetLineInfoUseCase @Inject constructor(
    private val repository: MainRepository,
) {
    suspend operator fun invoke(): Result<List<HomeLineInfo>> {
        return repository.getAllLineInfo()
    }
}
