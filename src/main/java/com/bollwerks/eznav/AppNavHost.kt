package com.bollwerks.eznav

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(
    navHostConfig: NavHostConfig,
    navController: NavHostController,
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
                config.content()
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
    val content: @Composable () -> Unit,
)