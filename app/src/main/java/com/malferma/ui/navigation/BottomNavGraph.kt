package com.malferma.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.malferma.ui.screens.ChartScreen
import com.malferma.ui.screens.HomeScreen
import com.malferma.ui.screens.ProfileScreen
import com.malferma.ui.screens.SettingsScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Subnetting.route
    ) {
        composable(route = Screen.Subnetting.route) {
            HomeScreen()
        }
        composable(route = Screen.Exercise.route) {
            ProfileScreen()
        }
        composable(route = Screen.QuickAnswer.route) {
            SettingsScreen()
        }
        composable(route = Screen.Chart.route) {
            ChartScreen()
        }
    }
}