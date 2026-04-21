package com.ssun.subway.home

import com.ssun.subway.core.base.UiEvent
import com.ssun.subway.core.base.UiSideEffect
import com.ssun.subway.core.base.UiState

class HomeContract {
    data class HomeUiState(
        val subwayLines: List<String> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
    ) : UiState

    sealed interface HomeUiEvent : UiEvent {
        data object LoadSubwayLines : HomeUiEvent
    }

    sealed class HomeSideEffect : UiSideEffect {
        data object ShowToast : HomeSideEffect()
        data object NavigateToDetail : HomeSideEffect()
    }
}
