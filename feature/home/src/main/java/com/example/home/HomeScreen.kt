package com.example.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
    modifier: Modifier = Modifier,
    onSubwayLineSelected: (String) -> Unit,
) {

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Home",
                modifier = Modifier.padding(innerPadding),
            )

            // 선택된 노선 전달
            Button(onClick = { onSubwayLineSelected("1호선") }) {
                Text("1호선 역 정보로 이동")
            }
            Button(onClick = { onSubwayLineSelected("2호선") }) {
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
