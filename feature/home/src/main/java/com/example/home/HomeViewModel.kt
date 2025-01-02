package com.example.home

import com.example.core.base.BaseViewModel
import com.example.domain.usecase.GetLineInfoUseCase
import com.example.home.HomeContract.HomeSideEffect
import com.example.home.HomeContract.HomeUiEvent
import com.example.home.HomeContract.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLineInfoUseCase: GetLineInfoUseCase,
) : BaseViewModel<HomeUiEvent, HomeUiState, HomeSideEffect>(HomeUiState) {

    override suspend fun handleEvent(event: HomeUiEvent) {
        TODO("Not yet implemented")
    }
}
