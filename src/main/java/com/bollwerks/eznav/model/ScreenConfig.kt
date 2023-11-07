package com.bollwerks.eznav.model

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController

data class ScreenConfig(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val content: @Composable (NavController, DrawerState, ViewModelProvider.Factory) -> Unit,
)