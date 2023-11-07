package com.bollwerks.eznav

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bollwerks.eznav.model.EzConfig
import com.bollwerks.eznav.model.toDrawerConfig
import com.bollwerks.eznav.model.toNavHostConfig

@Composable
fun EzNav(
    config: EzConfig,
) {
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController: NavHostController = rememberNavController()
    val drawerConfig = remember { config.toDrawerConfig() }
    val navHostConfig = remember { config.toNavHostConfig() }

    EzDrawer(
        navHostConfig = navHostConfig,
        navController = navController,
        drawerState = drawerState,
        drawerConfig = drawerConfig,
    )
}