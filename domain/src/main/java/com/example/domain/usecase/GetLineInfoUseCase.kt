package com.example.domain.usecase

import com.example.domain.model.HomeLineInfo
import com.example.domain.repository.MainRepository
import javax.inject.Inject

class GetLineInfoUseCase @Inject constructor(
    private val repository: MainRepository,
) {
    suspend operator fun invoke(): Result<List<HomeLineInfo>> {
        return repository.getAllLineInfo()
    }
}
