package com.github.lupuuss.kflux.core.middleware

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.scope.DispatchScope
import com.github.lupuuss.kflux.core.scope.consume
import com.github.lupuuss.kflux.core.scope.pass

inline fun <reified T : Action, State : Any> consumingMiddleware(
    crossinline block: DispatchScope<State>.(T) -> Unit
) = Middleware<State> {
    if (it !is T) pass() else {
        block(it)
        consume()
    }
}

inline fun <State : Any> translucentMiddleware(
    crossinline block: DispatchScope<State>.(Action) -> Unit
) = Middleware<State> {
    block(it)
    pass()
}