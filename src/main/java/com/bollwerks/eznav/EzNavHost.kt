package com.bollwerks.eznav

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun EzNavHost(
    navHostConfig: NavHostConfig,
    navController: NavHostController,
    drawerState: DrawerState,
) {
    NavHost(
        navController = navController,
        startDestination = navHostConfig.startDestination,
    ) {
        navHostConfig.composableConfigs.forEach { config ->
            composable(
                route = config.route,
                arguments = config.arguments,
            ) {
                config.content(navController, drawerState)
            }
        }
    }
}

data class NavHostConfig(
    val startDestination: String,
    val composableConfigs: List<NavComposableConfig>,
)

data class NavComposableConfig(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val content: @Composable (NavController, DrawerState) -> Unit,
)