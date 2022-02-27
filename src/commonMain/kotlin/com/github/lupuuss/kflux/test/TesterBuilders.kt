package com.github.lupuuss.kflux.test

import com.github.lupuuss.kflux.core.context.DispatchContext
import com.github.lupuuss.kflux.core.context.EmptyDispatchContext
import com.github.lupuuss.kflux.core.middleware.Middleware

fun <State : Any> Middleware<State>.tester(
    state: State,
    context: DispatchContext = EmptyDispatchContext,
    scope: TestDispatchScope<State> = DefaultTestDispatchScope(state, context)
) = MiddlewareTester(scope, this)