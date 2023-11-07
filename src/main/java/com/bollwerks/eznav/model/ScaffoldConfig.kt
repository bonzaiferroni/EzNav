package com.bollwerks.eznav.model

import androidx.compose.runtime.Composable

data class ScaffoldConfig(
    val title: String,
    val titleContent: @Composable (() -> Unit)? = null,
)