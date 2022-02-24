package com.github.lupuuss.kflux.thunk

import com.github.lupuuss.kflux.core.scope.DispatchScope
import kotlinx.coroutines.CoroutineStart
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun <State : Any> thunkExec(block: DispatchScope<State>.() -> Unit) = object : Thunk.Executable<State>() {
    override fun DispatchScope<State>.onExecute() = block()
}

fun <State : Any> thunkSuspend(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    coroutineStart: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend DispatchScope<State>.() -> Unit
) = object : Thunk.Suspendable<State>(coroutineContext, coroutineStart) {
    override suspend fun DispatchScope<State>.onExecuteSuspendable() = block()
}