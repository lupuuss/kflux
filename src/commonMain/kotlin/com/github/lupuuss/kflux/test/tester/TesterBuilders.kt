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
) = MiddlewareTester(defaultScope(state, context), this)

fun <State> Thunk.Executable<State>.tester(
    state: State,
    context: DispatchContext = EmptyDispatchContext,
) = ThunkExecutableTester(defaultScope(state, context), this)

fun <State> Thunk.Suspendable<State>.tester(
    state: State,
    context: DispatchContext = EmptyDispatchContext,
) = ThunkSuspendableTester(defaultScope(state, context), this)

fun <State> Thunk.Executable<State>.test(
    state: State,
    context: DispatchContext = EmptyDispatchContext,
    checks: ActionsAssertScope.() -> Unit,
) = ThunkExecutableTester(defaultScope(state, context), this).test(checks = checks)

suspend fun <State> Thunk.Suspendable<State>.test(
    state: State,
    context: DispatchContext = EmptyDispatchContext,
    checks: ActionsAssertScope.() -> Unit,
) = ThunkSuspendableTester(defaultScope(state, context), this).test(checks = checks)

@ExperimentalCoroutinesApi
fun <State> Thunk.Suspendable<State>.coroutineTest(
    state: State,
    context: DispatchContext = EmptyDispatchContext,
    checks: ActionsAssertScope.() -> Unit,
) = ThunkSuspendableTester(defaultScope(state, context), this).coroutineTest(state, context, checks)

private fun <State> defaultScope(state: State, context: DispatchContext) = DefaultTestDispatchScope(state, context)