package com.example.home

import com.example.core.base.BaseViewModel
import com.example.home.HomeContract.HomeSideEffect
import com.example.home.HomeContract.HomeUiEvent
import com.example.home.HomeContract.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor() :
    BaseViewModel<HomeUiEvent, HomeUiState, HomeSideEffect>(
        HomeUiState
    ) {

    override suspend fun handleEvent(event: HomeUiEvent) {
        TODO("Not yet implemented")
    }
}
