package com.ssun.subway.detail

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ssun.subway.core.theme.SubwayTheme
import com.ssun.subway.core.util.CenteredStateContent
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
            viewModel.checkStationInfoState(line)
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
            val bodyModifier = Modifier
                .fillMaxWidth()
                .weight(1f)
            val errorMessage = uiState.errorMessage

            Text(
                text = uiState.selectedLine.ifBlank { stringResource(R.string.detail_line_info_title) },
                style = MaterialTheme.typography.headlineMedium,
            )

            when {
                uiState.isLoading -> CenteredStateContent(modifier = bodyModifier) {
                    CircularProgressIndicator()
                }

                errorMessage != null -> CenteredStateContent(modifier = bodyModifier) {
                    Text(text = errorMessage)
                }

                uiState.stationInfo.isEmpty() -> CenteredStateContent(modifier = bodyModifier) {
                    uiState.noticeMessage?.let { message ->
                        Text(text = message)
                    }
                    Text(text = stringResource(R.string.detail_station_info_empty))
                }

                else -> DetailStationList(
                    stationInfo = uiState.stationInfo,
                    noticeMessage = uiState.noticeMessage,
                    modifier = bodyModifier,
                )
            }
        }
    }
}

@Composable
private fun DetailStationList(
    stationInfo: List<DetailContract.DetailStationCardInfo>,
    noticeMessage: String?,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        noticeMessage?.let { message ->
            item(key = "notice_message") {
                Text(text = message)
            }
        }

        items(
            items = stationInfo,
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

@Composable
private fun buildStatsText(
    getOnCount: Long?,
    getOffCount: Long?,
): String {
    if (getOnCount == null || getOffCount == null) {
        return stringResource(R.string.detail_station_stats_unavailable)
    }

    val formatter = NumberFormat.getNumberInstance(Locale.KOREA)
    return stringResource(
        R.string.detail_station_stats_format,
        formatter.format(getOnCount),
        formatter.format(getOffCount),
    )
}
