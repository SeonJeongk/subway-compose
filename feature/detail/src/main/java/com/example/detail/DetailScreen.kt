package com.example.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.theme.SubwayTheme
import com.example.detail.DetailContract.DetailUiEvent
import java.text.NumberFormat
import java.util.Locale

@Composable
fun DetailScreen(
    subwayLine: String?,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(subwayLine) {
        subwayLine?.let { line ->
            viewModel.dispatchEvent(DetailUiEvent.LoadLineInfo(line))
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = uiState.selectedLine.ifBlank { subwayLine ?: "호선 정보" },
                style = MaterialTheme.typography.headlineMedium,
            )

            if (uiState.isLoading) {
                CircularProgressIndicator()
            }

            uiState.errorMessage?.let { message ->
                Text(text = message)
            }

            uiState.noticeMessage?.let { message ->
                Text(text = message)
            }

            if (!uiState.isLoading && uiState.stationInfo.isEmpty() && uiState.errorMessage == null) {
                Text(text = "표시할 역 정보가 없습니다.")
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(
                    items = uiState.stationInfo,
                    key = { station -> station.stationName },
                ) { station ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text(
                                text = buildStationTitle(
                                    stationName = station.stationName,
                                    stationNameEng = station.stationNameEng,
                                ),
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Text(
                                text = buildStatsText(
                                    getOnCount = station.getOnCount,
                                    getOffCount = station.getOffCount,
                                ),
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    SubwayTheme {
        DetailScreen(subwayLine = "2호선")
    }
}

private fun buildStationTitle(
    stationName: String,
    stationNameEng: String,
): String {
    if (stationNameEng.isBlank()) {
        return stationName
    }

    return "$stationName ($stationNameEng)"
}

private fun buildStatsText(
    getOnCount: Long?,
    getOffCount: Long?,
): String {
    if (getOnCount == null || getOffCount == null) {
        return "승하차 통계가 제공되지 않습니다."
    }

    val formatter = NumberFormat.getNumberInstance(Locale.KOREA)
    return "승차 ${formatter.format(getOnCount)}명 / 하차 ${formatter.format(getOffCount)}명"
}
