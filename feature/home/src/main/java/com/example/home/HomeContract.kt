package com.example.home

import com.example.core.base.UiEvent
import com.example.core.base.UiSideEffect
import com.example.core.base.UiState


class HomeContract {
    data object HomeUiState : UiState

    data object HomeUiEvent : UiEvent

    sealed class HomeSideEffect : UiSideEffect {
        object ShowToast : HomeSideEffect()
        object NavigateToDetail : HomeSideEffect()
    }
}
