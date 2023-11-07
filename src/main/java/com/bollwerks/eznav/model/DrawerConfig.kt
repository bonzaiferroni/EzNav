package com.bollwerks.eznav.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.bollwerks.eznav.EzRoute

data class DrawerConfig(
    val mainAppIcon: (@Composable () -> Painter)? = null,
    val drawerItems: List<DrawerItemConfig> = emptyList(),
)

data class DrawerItemConfig(
    val drawerOption: EzRoute,
    val title: String,
    val icon: ImageVector,
)