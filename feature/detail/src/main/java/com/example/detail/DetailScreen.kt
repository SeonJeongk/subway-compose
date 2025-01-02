package com.example.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.theme.SubwayTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    subwayLine: String?,
    viewModel: DetailViewModel = hiltViewModel(),
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
                "Detail",
                modifier = Modifier.padding(innerPadding),
            )
            Text(text = "$subwayLine 디테일 화면")
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
