package com.github.lupuuss.kflux.thunk

import com.github.lupuuss.kflux.core.context.element.coroutineScope
import com.github.lupuuss.kflux.core.middleware.consumingMiddleware
import kotlinx.coroutines.launch

fun <State : Any> thunkMiddleware() = consumingMiddleware<Thunk, State> { thunk ->
    when (thunk) {
        is Thunk.CoreExecutable -> with(thunk) { execute() }
        is Thunk.CoreSuspendable -> coroutineScope.launch(thunk.coroutineContext, thunk.coroutineStart) {
            with(thunk) { executeSuspendable() }
        }
    }
}