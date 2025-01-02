package com.example.detail

import com.example.core.base.UiEvent
import com.example.core.base.UiSideEffect
import com.example.core.base.UiState

class DetailContract {
    data object DetailUiState : UiState

    data object DetailUiEvent : UiEvent

    sealed class DetailSideEffect : UiSideEffect {
        object ShowToast : DetailSideEffect()
        object NavigateToBack: DetailSideEffect()
    }
}
