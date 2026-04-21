package com.ssun.subway.detail

import com.ssun.subway.core.base.BaseViewModel
import com.ssun.subway.detail.DetailContract.DetailStationCardInfo
import com.ssun.subway.detail.DetailContract.DetailSideEffect
import com.ssun.subway.detail.DetailContract.DetailUiEvent
import com.ssun.subway.detail.DetailContract.DetailUiState
import com.ssun.subway.domain.usecase.GetStationDetailUseCase
import com.ssun.subway.domain.usecase.GetLineInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getLineInfoUseCase: GetLineInfoUseCase,
    private val getStationDetailUseCase: GetStationDetailUseCase,
) : BaseViewModel<DetailUiEvent, DetailUiState, DetailSideEffect>(DetailUiState()) {

    fun checkStationInfoState(subwayLine: String) {
        dispatchEvent(DetailUiEvent.LoadLineInfo(subwayLine))
    }

    override suspend fun handleEvent(event: DetailUiEvent) {
        when (event) {
            is DetailUiEvent.LoadLineInfo -> loadLineInfo(event.subwayLine)
        }
    }

    private suspend fun loadLineInfo(subwayLine: String) {
        updateState {
            copy(
                selectedLine = subwayLine,
                isLoading = true,
                noticeMessage = null,
                errorMessage = null,
            )
        }

        val selectedLineCandidates = buildSelectedLineCandidates(subwayLine)
        val queryDate = resolveLatestAvailableDate()

        coroutineScope {
            val lineInfoDeferred = async { getLineInfoUseCase() }
            val stationDetailDeferred = async { getStationDetailUseCase(queryDate, subwayLine) }

            val lineInfoResult = lineInfoDeferred.await()
            val stationDetailResult = stationDetailDeferred.await()

            lineInfoResult
                .onSuccess { lineInfo ->
                    val filteredStations = lineInfo.filter { station ->
                        station.lineNumber in selectedLineCandidates
                    }

                    val stationDetailMap = stationDetailResult
                        .getOrDefault(emptyList())
                        .associateBy { station -> station.stationName }

                    val stationCards = if (stationDetailMap.isNotEmpty()) {
                        stationDetailResult
                            .getOrDefault(emptyList())
                            .map { station ->
                                val matchedStation = filteredStations.firstOrNull { info ->
                                    info.stationName == station.stationName
                                }

                                DetailStationCardInfo(
                                    stationName = station.stationName,
                                    stationNameEng = matchedStation?.stationNameEng.orEmpty(),
                                    getOnCount = station.getOnCount,
                                    getOffCount = station.getOffCount,
                                )
                            }
                    } else {
                        filteredStations.map { station ->
                            DetailStationCardInfo(
                                stationName = station.stationName,
                                stationNameEng = station.stationNameEng,
                                getOnCount = null,
                                getOffCount = null,
                            )
                        }
                    }

                    val noticeMessage = when {
                        stationDetailResult.isFailure -> "승하차 통계를 불러오지 못했습니다. 역 정보만 표시합니다."
                        stationDetailMap.isEmpty() -> "이 호선은 승하차 통계가 제공되지 않습니다."
                        else -> null
                    }

                    updateState {
                        copy(
                            stationInfo = stationCards,
                            isLoading = false,
                            noticeMessage = noticeMessage,
                            errorMessage = null,
                        )
                    }
                }
                .onFailure {
                    updateState {
                        copy(
                            stationInfo = emptyList(),
                            isLoading = false,
                            noticeMessage = null,
                            errorMessage = "역 정보를 불러오지 못했습니다.",
                        )
                    }
                }
        }
    }

    private fun buildSelectedLineCandidates(subwayLine: String): Set<String> {
        val normalizedDigits = subwayLine.filter(Char::isDigit)
        if (normalizedDigits.isEmpty()) {
            return setOf(subwayLine)
        }

        // 열린데이터 응답은 01호선 형태를 함께 사용하므로 버튼 문자열도 같이 비교합니다.
        val paddedDigits = normalizedDigits.padStart(2, '0')
        return setOf(
            subwayLine,
            "${normalizedDigits}호선",
            "${paddedDigits}호선",
        )
    }

    private fun resolveLatestAvailableDate(): String {
        // 서울 열린데이터광장은 일 단위 데이터가 보통 3일 전 기준으로 적재됩니다.
        return LocalDate.now(ZoneId.of("Asia/Seoul"))
            .minusDays(3)
            .format(DateTimeFormatter.BASIC_ISO_DATE)
    }
}
