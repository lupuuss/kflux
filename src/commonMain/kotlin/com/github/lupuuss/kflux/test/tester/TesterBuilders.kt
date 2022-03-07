package com.github.lupuuss.kflux.test.tester

import com.github.lupuuss.kflux.core.context.DispatchContext
import com.github.lupuuss.kflux.core.context.EmptyDispatchContext
import com.github.lupuuss.kflux.core.middleware.Middleware
import com.github.lupuuss.kflux.test.DefaultTestDispatchScope
import com.github.lupuuss.kflux.test.TestDispatchScope
import com.github.lupuuss.kflux.thunk.Thunk

fun <State> Middleware<State>.tester(
    state: State,
    context: DispatchContext = EmptyDispatchContext,
    scope: TestDispatchScope<State> = DefaultTestDispatchScope(state, context)
) = MiddlewareTester(scope, this)

fun <State> Thunk.Executable<State>.tester(
    state: State,
    context: DispatchContext = EmptyDispatchContext,
    scope: TestDispatchScope<State> = DefaultTestDispatchScope(state, context)
) = ThunkExecutableTester(scope, this)

fun <State> Thunk.Suspendable<State>.tester(
    state: State,
    context: DispatchContext = EmptyDispatchContext,
    scope: TestDispatchScope<State> = DefaultTestDispatchScope(state, context)
) = ThunkSuspendableTester(scope, this)
