package com.bollwerks.eznav.model

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import com.bollwerks.eznav.EzRoute

data class ScreenConfig(
    val route: EzRoute,
    val content: @Composable (NavController, DrawerState, ViewModelProvider.Factory) -> Unit,
    val drawerLink: DrawerLinkConfig? = null,
    val isDefaultRoute: Boolean = false,
    val scaffold: ScaffoldConfig? = null,
)