package com.example.detail

import com.example.core.base.BaseViewModel
import com.example.detail.DetailContract.DetailSideEffect
import com.example.detail.DetailContract.DetailUiEvent
import com.example.detail.DetailContract.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor() :
    BaseViewModel<DetailUiEvent, DetailUiState, DetailSideEffect>(
        DetailUiState,
    ) {

    override suspend fun handleEvent(event: DetailUiEvent) {
        TODO("Not yet implemented")
    }
}
