package com.ssun.subway.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ssun.subway.detail.DetailScreen
import com.ssun.subway.home.HomeScreen

enum class MainScreenRoute(val route: String) {
    HOME("home"),
    DETAIL("detail/{subwayLine}")
}

@Composable
internal fun MainScreen(
    navController: NavHostController = rememberNavController(),
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { padding ->
            NavHost(
                navController = navController,
                startDestination = MainScreenRoute.HOME.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(route = MainScreenRoute.HOME.route) {
                    HomeScreen(
                        onSubwayLineSelected = { subwayLine ->
                            navController.navigate("detail/$subwayLine")
                        }
                    )
                }

                composable(route = MainScreenRoute.DETAIL.route) { backStackEntry ->
                    val subwayLine = backStackEntry.arguments?.getString("subwayLine")
                    DetailScreen(subwayLine = subwayLine)
                }
            }
        },
    )
}
