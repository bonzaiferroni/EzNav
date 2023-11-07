package com.bollwerks.eznav

import android.content.Context
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

data class NavHostConfig(
    val initialRoute: EzRoute,
    val composableConfigs: List<NavComposableConfig>,
    val vmFactoryBuilder: (Context) -> ViewModelProvider.Factory,
)

data class NavComposableConfig(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val content: @Composable (NavController, DrawerState, ViewModelProvider.Factory) -> Unit,
)

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
        navHostConfig.composableConfigs.forEach { config ->
            composable(
                route = config.route,
                arguments = config.arguments,
            ) {
                config.content(navController, drawerState, vmFactory)
            }
        }
    }
}