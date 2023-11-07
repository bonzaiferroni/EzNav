package com.bollwerks.eznav.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.bollwerks.eznav.EzRoute

data class DrawerConfig(
    val mainAppIcon: (@Composable () -> Painter)? = null,
    val drawerItems: List<DrawerLinkConfig> = emptyList(),
)

data class DrawerLinkConfig(
    val drawerOption: EzRoute,
    val title: String,
    val emoji: String,
)

fun EzConfig.toDrawerConfig(): DrawerConfig {
    return DrawerConfig(
        mainAppIcon = this.mainAppIcon,
        drawerItems = this.screens.mapNotNull { it.drawerLink }
    )
}