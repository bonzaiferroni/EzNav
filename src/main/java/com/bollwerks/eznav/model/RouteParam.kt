package com.bollwerks.eznav.model

import androidx.navigation.NavType
import java.time.LocalDate

open class RouteParam<T>(
    val name: String,
    val type: NavType<T>,
    val defaultValue: T? = null,
    val isOptional: Boolean = false,
)

class EnumParam<T : Enum<T>>(
    name: String,
    defaultValue: T? = null,
    isOptional: Boolean = false,
) : RouteParam<String?>(
    name = name,
    type = NavType.StringType,
    defaultValue = defaultValue?.toString(),
    isOptional = isOptional,
)

class DateParam(
    name: String,
    defaultValue: LocalDate? = null,
    isOptional: Boolean = false,
) : RouteParam<String?>(
    name = name,
    type = NavType.StringType,
    defaultValue = defaultValue?.toString(),
    isOptional = isOptional,
)