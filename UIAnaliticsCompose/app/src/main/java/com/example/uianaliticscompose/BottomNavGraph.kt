package com.example.uianaliticscompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.screen_route,
        modifier = Modifier.semantics { testTagsAsResourceId = true }
    ) {
        composable(route = BottomNavItem.Home.screen_route) {
            HomeScreen(navController, AppState.destinationList)
        }

        composable(route = BottomNavItem.Traveled.screen_route) {
            HomeScreen(navController, AppState.destinationList.filter { it.isVisited })
        }

        composable(route = BottomNavItem.AddDestination.screen_route) {
            AddDestinationScreen(navController)
        }

        composable(route = BottomNavItem.DetailDestination.screen_route) {
            DetailScreen(navController)
        }
    }
}