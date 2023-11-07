package com.bollwerks.eznav.model

import androidx.navigation.NavType
import java.time.LocalDate

open class RouteParam<T>(
    val name: String,
    val type: NavType<T>,
) {
    var isOptional: Boolean = false
        private set
    var defaultValue: T? = null
        private set

    constructor(
        name: String,
        type: NavType<T>,
        isOptional: Boolean,
        defaultValue: T? = null,
    ) : this(name, type) {
        this.isOptional = isOptional
        this.defaultValue = defaultValue
    }
}

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