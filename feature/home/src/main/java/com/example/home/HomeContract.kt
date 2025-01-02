package com.example.home

import com.example.core.base.UiEvent
import com.example.core.base.UiSideEffect
import com.example.core.base.UiState


class HomeContract {
    data object HomeUiState : UiState

    data object HomeUiEvent : UiEvent

    sealed class HomeSideEffect : UiSideEffect {
        data object ShowToast : HomeSideEffect()
        data object NavigateToDetail : HomeSideEffect()
    }
}
