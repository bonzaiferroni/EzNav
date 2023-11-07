package com.bollwerks.eznav

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bollwerks.eznav.model.NavHostConfig

@Composable
fun EzNavHost(
    navHostConfig: NavHostConfig,
    navController: NavHostController,
    drawerState: DrawerState,
) {
    val ctx = LocalContext.current
    val vmFactory = remember { navHostConfig.vmFactoryBuilder(ctx) }

    NavHost(
        navController = navController,
        startDestination = navHostConfig.initialRoute.route,
    ) {
        navHostConfig.screenConfigs.forEach { config ->
            composable(
                route = config.route,
                arguments = config.arguments,
            ) {
                config.content(navController, drawerState, vmFactory)
            }
        }
    }
}