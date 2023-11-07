package com.bollwerks.eznav

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun EzNav(
    config: EzConfig,
) {
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController: NavHostController = rememberNavController()
    EzDrawer(
        drawerConfig = config.drawerConfig,
        navHostConfig = config.navHostConfig,
        navController = navController,
        drawerState = drawerState,
        initialRoute = config.navHostConfig.initialRoute,
    )
}