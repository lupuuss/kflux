package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.core.store.builder.buildStore
import com.github.lupuuss.kflux.task.taskMiddleware
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun appStore() = buildStore {
    AppState() reducedBy ::appReducer
    middlewares {
        +debugMiddleware()
        +taskMiddleware { Clock.System.now().toLocalDateTime(TimeZone.UTC) }
    }
}