package com.github.lupuuss.kflux.init

import com.github.lupuuss.kflux.core.middleware.Middleware
import com.github.lupuuss.kflux.core.middleware.translucentMiddleware
import com.github.lupuuss.kflux.core.scope.DispatchScope

fun <State : Any> initMiddleware(block: DispatchScope<State>.() -> Middleware<State>): Middleware<State> {
    var currentMiddleware: Middleware<State>
    val initMiddleware = translucentMiddleware<State> {
        currentMiddleware = block()
    }
    currentMiddleware = initMiddleware
    return Middleware { currentMiddleware.run { process(it) } }
}