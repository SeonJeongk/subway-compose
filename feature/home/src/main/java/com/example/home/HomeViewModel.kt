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
) : BaseViewModel<HomeUiEvent, HomeUiState, HomeSideEffect>(HomeUiState()) {

    override suspend fun handleEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.LoadSubwayLines -> loadSubwayLines()
        }
    }

    private suspend fun loadSubwayLines() {
        updateState {
            copy(
                isLoading = true,
                errorMessage = null,
            )
        }

        getLineInfoUseCase()
            .onSuccess { lineInfo ->
                updateState {
                    copy(
                        subwayLines = lineInfo
                            .map { station -> normalizeLineName(station.lineNumber) }
                            .distinct()
                            .sortedWith(
                                compareBy<String>(
                                    { line -> lineSortOrder(line) },
                                    { line -> line.length },
                                    { line -> line },
                                ),
                            ),
                        isLoading = false,
                    )
                }
            }
            .onFailure { throwable ->
                updateState {
                    copy(
                        subwayLines = emptyList(),
                        isLoading = false,
                        errorMessage = throwable.message ?: "호선 정보를 불러오지 못했습니다.",
                    )
                }
            }
    }

    private fun normalizeLineName(lineNumber: String): String {
        val digits = lineNumber.filter(Char::isDigit)
        if (digits.isNotEmpty() && lineNumber.endsWith("호선")) {
            return "${digits.toInt()}호선"
        }

        return lineNumber
    }

    private fun lineSortOrder(lineName: String): Int {
        val digits = lineName.filter(Char::isDigit)
        if (digits.isNotEmpty() && lineName.endsWith("호선")) {
            return digits.toInt()
        }

        return Int.MAX_VALUE
    }
}
