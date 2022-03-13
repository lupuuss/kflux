package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.core.store.builder.buildStore
import com.github.lupuuss.kflux.data.DataSourceFactory
import com.github.lupuuss.kflux.kodein.KodeinDI
import com.github.lupuuss.kflux.thunk.thunkMiddleware
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.kodein.di.DI
import org.kodein.di.bindSingleton


fun appStore() = buildStore {
    AppState() reducedBy ::appReducer
    middlewares {
        +debugMiddleware()
        +thunkMiddleware<AppState>()
    }

    context {
        +KodeinDI(buildDi())
    }
}

private fun buildDi() = DI {
    bindSingleton { DateProvider { Clock.System.now().toLocalDateTime(TimeZone.UTC) } }
    bindSingleton<DataSourceFactory> { DataSourceFactory.Default }
}