package com.github.lupuuss.kflux.thunk

import com.github.lupuuss.kflux.core.context.element.coroutineScope
import com.github.lupuuss.kflux.core.middleware.consumingMiddleware
import kotlinx.coroutines.launch

fun <State : Any> thunkMiddleware() = consumingMiddleware<Thunk, State> {
    when (it) {
        is Thunk.CoreExecutable -> with(it) { execute() }
        is Thunk.CoreSuspendable -> coroutineScope.launch(it.coroutineContext, it.coroutineStart) {
            with(it) { executeSuspendable() }
        }
    }
}