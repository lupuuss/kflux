package com.github.lupuuss.kflux.core.middleware

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.scope.DispatchScope

inline fun <State, reified T : Action,> consumingMiddleware(
    crossinline block: DispatchScope<State>.(T) -> Unit
) = Middleware<State> {
    if (it !is T) pass() else {
        block(it)
        consume()
    }
}

inline fun <State> translucentMiddleware(
    crossinline block: DispatchScope<State>.(Action) -> Unit
) = Middleware<State> {
    block(it)
    pass()
}