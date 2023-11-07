package com.bollwerks.eznav

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun EzNav(
    navHostConfig: NavHostConfig,
    drawerConfig: DrawerConfig,
    destinations: List<AppDestination>,
    navController: NavHostController = rememberNavController(),
) {
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    EzDrawer(
        drawerConfig = drawerConfig,
        navHostConfig = navHostConfig,
        destinations = destinations,
        navController = navController,
        drawerState = drawerState
    )
}