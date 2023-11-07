package com.bollwerks.eznav.model

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.ViewModelProvider

class EzConfig(
    val mainAppIcon: (@Composable () -> Painter)? = null,
    val screens: List<ScreenConfig>,
    val vmFactoryBuilder: (Context) -> ViewModelProvider.Factory,
)