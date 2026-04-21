package com.example.detail

import com.example.core.base.UiEvent
import com.example.core.base.UiSideEffect
import com.example.core.base.UiState
class DetailContract {
    data class DetailStationCardInfo(
        val stationName: String,
        val stationNameEng: String,
        val getOnCount: Long,
        val getOffCount: Long,
    )

    data class DetailUiState(
        val selectedLine: String = "",
        val stationInfo: List<DetailStationCardInfo> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
    ) : UiState

    sealed interface DetailUiEvent : UiEvent {
        data class LoadLineInfo(val subwayLine: String) : DetailUiEvent
    }

    sealed class DetailSideEffect : UiSideEffect {
        data object ShowToast : DetailSideEffect()
        data object NavigateToBack : DetailSideEffect()
    }
}
