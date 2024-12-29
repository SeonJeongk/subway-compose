package com.example.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.theme.SubwayTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Text(
            "Home",
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SubwayTheme {
        HomeScreen()
    }
}
