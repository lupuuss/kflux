package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.core.context.element.DispatchCoroutineScope
import com.github.lupuuss.kflux.core.store.builder.buildStore
import com.github.lupuuss.kflux.data.DataSourceFactory
import com.github.lupuuss.kflux.kodein.KodeinDI
import com.github.lupuuss.kflux.log.logMiddleware
import com.github.lupuuss.kflux.thunk.thunkMiddleware
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.bindSingleton

fun appStore(scope: CoroutineScope) = buildStore {
    AppState() reducedBy ::appReducer
    middlewares {
        +logMiddleware<AppState>()
        +thunkMiddleware<AppState>()
    }
    context {
        +DispatchCoroutineScope(scope)
        +KodeinDI {
            bindSingleton<DataSourceFactory> { DataSourceFactory.Default }
        }
    }
}