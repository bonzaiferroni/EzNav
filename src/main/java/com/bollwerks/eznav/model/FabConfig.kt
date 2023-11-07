package com.bollwerks.eznav.model

import androidx.compose.ui.graphics.vector.ImageVector

data class FabConfig(
    val icon: ImageVector,
    val contentDescription: String,
    val onClick: () -> Unit
)