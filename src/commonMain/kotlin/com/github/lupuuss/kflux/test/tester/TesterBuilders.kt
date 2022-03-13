package com.github.lupuuss.kflux.test.tester

import com.github.lupuuss.kflux.core.context.DispatchContext
import com.github.lupuuss.kflux.core.context.EmptyDispatchContext
import com.github.lupuuss.kflux.core.middleware.Middleware
import com.github.lupuuss.kflux.test.ActionsAssertScope
import com.github.lupuuss.kflux.test.DefaultTestDispatchScope
import com.github.lupuuss.kflux.test.TestDispatchScope
import com.github.lupuuss.kflux.thunk.Thunk
import kotlinx.coroutines.ExperimentalCoroutinesApi

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

fun <State> Thunk.Executable<State>.test(
    state: State,
    context: DispatchContext = EmptyDispatchContext,
    scope: TestDispatchScope<State> = DefaultTestDispatchScope(state, context),
    checks: ActionsAssertScope.() -> Unit,
) = ThunkExecutableTester(scope, this).test(checks = checks)

suspend fun <State> Thunk.Suspendable<State>.test(
    state: State,
    context: DispatchContext = EmptyDispatchContext,
    scope: TestDispatchScope<State> = DefaultTestDispatchScope(state, context),
    checks: ActionsAssertScope.() -> Unit,
) = ThunkSuspendableTester(scope, this).test(checks = checks)

@ExperimentalCoroutinesApi
fun <State> Thunk.Suspendable<State>.coroutineTest(
    state: State,
    context: DispatchContext = EmptyDispatchContext,
    scope: TestDispatchScope<State> = DefaultTestDispatchScope(state, context),
    checks: ActionsAssertScope.() -> Unit,
) = ThunkSuspendableTester(scope, this).coroutineTest(state, context, checks)
