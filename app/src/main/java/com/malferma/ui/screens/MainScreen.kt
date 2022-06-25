package com.malferma.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.malferma.ui.navigation.BottomNavGraph
import com.malferma.ui.navigation.Screen
import com.malferma.ui.theme.COLOR_BottomNavMenu
import com.malferma.ui.theme.COLOR_BottomNavMenuNORMAL
import com.malferma.ui.theme.COLOR_BottomNavMenuSELECTED

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(
            navController = navController,
            screens = Screen.Items.list,
        ) }
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController, screens: List<Screen>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController,
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
       /* label = {
            Text(text = screen.title)
        },*/
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = COLOR_BottomNavMenuNORMAL,//LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        selectedContentColor = COLOR_BottomNavMenuSELECTED,
        modifier = Modifier.background(COLOR_BottomNavMenu),
    )
}

