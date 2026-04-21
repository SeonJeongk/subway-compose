package com.example.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.theme.SubwayTheme

@Composable
fun HomeScreen(
    onSubwayLineSelected: (String) -> Unit,
) {

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically,
            ),
        ) {
            Text(
                text = "서울 지하철 호선별 역 정보",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(text = "확인할 호선을 선택해 주세요.")
            Spacer(Modifier.height(30.dp))
            Button(
                onClick = { onSubwayLineSelected("1호선") },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("1호선 역 정보로 이동")
            }
            Button(
                onClick = { onSubwayLineSelected("2호선") },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("2호선 역 정보로 이동")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SubwayTheme {
        HomeScreen(onSubwayLineSelected = {})
    }
}
