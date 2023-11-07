package com.bollwerks.eznav.model

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.bollwerks.eznav.EzRoute

data class NavHostConfig(
    val initialRoute: EzRoute,
    val screenConfigs: List<ScreenConfig>,
    val vmFactoryBuilder: (Context) -> ViewModelProvider.Factory,
)