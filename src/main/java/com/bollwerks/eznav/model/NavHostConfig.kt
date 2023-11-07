package com.bollwerks.eznav.model

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.bollwerks.eznav.EzRoute

data class NavHostConfig(
    val initialRoute: EzRoute,
    val screens: List<ScreenConfig>,
    val vmFactoryBuilder: (Context) -> ViewModelProvider.Factory,
)

fun EzConfig.toNavHostConfig(): NavHostConfig {
    return NavHostConfig(
        initialRoute = this.screens.firstOrNull { it.isDefaultRoute }?.route
            ?: this.screens.firstOrNull()?.route
            ?: throw IllegalStateException("You must define at least one screen"),
        screens = this.screens,
        vmFactoryBuilder = this.vmFactoryBuilder,
    )
}