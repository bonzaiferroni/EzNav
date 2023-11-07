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
        navHostConfig.screens.forEach { config ->
            composable(
                route = config.route.route,
                arguments = config.route.navArguments,
            ) {
                val scaffold = config.scaffold
                if (scaffold != null) {
                    EzScaffold(
                        drawerState = drawerState,
                        title = scaffold.title,
                        navController = navController,
                        titleContent = scaffold.titleContent,
                    ) {
                        config.content(navController, drawerState, vmFactory)
                    }
                } else {
                    config.content(navController, drawerState, vmFactory)
                }
            }
        }
    }
}