package com.github.lupuuss.kflux.thunk

import com.github.lupuuss.kflux.core.context.element.coroutineScope
import com.github.lupuuss.kflux.core.middleware.consumingMiddleware
import kotlinx.coroutines.launch

fun <State> thunkMiddleware() = consumingMiddleware<Thunk, State> { thunk ->
    when (thunk) {
        is Thunk.CoreExecutable -> thunk.run { execute() }
        is Thunk.CoreSuspendable -> coroutineScope.launch(thunk.coroutineContext, thunk.coroutineStart) {
            thunk.run { executeSuspendable() }
        }
    }
}