package com.github.lupuuss.kflux.closure

import com.github.lupuuss.kflux.core.middleware.Middleware
import com.github.lupuuss.kflux.core.middleware.translucentMiddleware
import com.github.lupuuss.kflux.core.scope.DispatchScope
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
fun <State> middlewareClosure(@BuilderInference block: DispatchScope<State>.() -> Middleware<State>): Middleware<State> {
    var currentMiddleware: Middleware<State>
    val initMiddleware = translucentMiddleware<State> {
        currentMiddleware = block().apply { process(it) }
    }
    currentMiddleware = initMiddleware
    return Middleware { currentMiddleware.run { process(it) } }
}