package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.core.store.builder.buildStore
import com.github.lupuuss.kflux.kodein.KodeinDI
import com.github.lupuuss.kflux.task.taskMiddleware
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.kodein.di.DI
import org.kodein.di.bindSingleton


fun appStore() = buildStore {
    AppState() reducedBy ::appReducer
    middlewares {
        +debugMiddleware()
        +taskMiddleware()
    }

    context {
        +KodeinDI(buildDi())
    }
}

private fun buildDi() = DI {
    bindSingleton { DateProvider { Clock.System.now().toLocalDateTime(TimeZone.UTC) } }
}