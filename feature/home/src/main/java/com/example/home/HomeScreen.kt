package com.example.home

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.theme.SubwayTheme
import com.example.home.HomeContract.HomeUiEvent
import com.example.home.HomeContract.HomeUiState

@Composable
fun HomeScreen(
    onSubwayLineSelected: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.dispatchEvent(HomeUiEvent.LoadSubwayLines)
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
            Text(
                text = "서울 지하철 호선별 역 정보",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 32.dp),
            )
            Text(text = "확인할 호선을 선택해 주세요.")

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                when {
                    uiState.isLoading -> {
                        CircularProgressIndicator()
                    }

                    uiState.errorMessage != null -> {
                        Text(text = uiState.errorMessage)
                    }

                    uiState.subwayLines.isEmpty() -> {
                        Text(text = "표시할 호선 정보가 없습니다.")
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(14.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            items(
                                items = uiState.subwayLines,
                                key = { line -> line },
                            ) { line ->
                                Button(
                                    onClick = { onSubwayLineSelected(line) },
                                    modifier = Modifier.fillMaxWidth(),
                                ) {
                                    Text("$line 역 정보로 이동")
                                }
                            }
                        }
                    }
                }
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
