package com.github.lupuuss.kflux.thunk

import com.github.lupuuss.kflux.core.context.element.coroutineScope
import com.github.lupuuss.kflux.core.middleware.consumingMiddleware
import kotlinx.coroutines.launch

fun <State> thunkMiddleware() = consumingMiddleware<State, Thunk> { thunk ->
    when (thunk) {
        is Thunk.Executable<*> -> thunk.cast<State>().run { execute() }
        is Thunk.Suspendable<*> -> coroutineScope.launch(thunk.coroutineContext, thunk.coroutineStart) {
            handleNesting(thunk.cast(), this@consumingMiddleware, this)
        }
    }
}

@Suppress("UNCHECKED_CAST")
internal inline fun <State> Thunk.Executable<*>.cast() = this as Thunk.Executable<State>

@Suppress("UNCHECKED_CAST")
internal inline fun <State> Thunk.Suspendable<*>.cast() = this as Thunk.Suspendable<State>
