package com.ssun.subway.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import com.ssun.subway.home.HomeContract.HomeUiState

@Composable
fun HomeScreen(
    onSubwayLineSelected: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.checkSubwayLinesState()
    }

    HomeContent(
        uiState = uiState,
        onSubwayLineSelected = onSubwayLineSelected,
    )
}

@Composable
private fun HomeContent(
    uiState: HomeUiState,
    onSubwayLineSelected: (String) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            val bodyModifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .weight(1f)
            val errorMessage = uiState.errorMessage

            Text(
                text = stringResource(R.string.home_subway_lines_title),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 32.dp),
            )
            Text(text = stringResource(R.string.home_subway_lines_description))

            when {
                uiState.isLoading -> CenteredStateContent(modifier = bodyModifier) {
                    CircularProgressIndicator()
                }

                errorMessage != null -> CenteredStateContent(modifier = bodyModifier) {
                    Text(text = errorMessage)
                }

                uiState.subwayLines.isEmpty() -> CenteredStateContent(modifier = bodyModifier) {
                    Text(text = stringResource(R.string.home_subway_lines_empty))
                }

                else -> HomeSubwayLineList(
                    subwayLines = uiState.subwayLines,
                    onSubwayLineSelected = onSubwayLineSelected,
                    modifier = bodyModifier,
                )
            }
        }
    }
}

@Composable
private fun HomeSubwayLineList(
    subwayLines: List<String>,
    onSubwayLineSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(
            items = subwayLines,
            key = { line -> line },
        ) { line ->
            Button(
                onClick = { onSubwayLineSelected(line) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(
                        R.string.home_subway_line_station_info,
                        line,
                    ),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SubwayTheme {
        HomeContent(
            uiState = HomeUiState(
                subwayLines = listOf("1호선", "2호선", "3호선", "수인분당선"),
            ),
            onSubwayLineSelected = {},
        )
    }
}
