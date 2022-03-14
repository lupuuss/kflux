package com.github.lupuuss.kflux.thunk

import com.github.lupuuss.kflux.core.scope.DispatchScope
import kotlinx.coroutines.CoroutineStart
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun <State> thunkExec(block: DispatchScope<State>.() -> Unit) = object : Thunk.Executable<State> {
    override fun DispatchScope<State>.execute() = block()
}

fun <State> thunkSuspend(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    coroutineStart: CoroutineStart = CoroutineStart.DEFAULT,
    nesting: Boolean = false,
    block: suspend DispatchScope<State>.() -> Unit
) = object : Thunk.Suspendable<State>(coroutineContext, coroutineStart, nesting) {
    override suspend fun DispatchScope<State>.executeSuspendable() = block()
}

infix fun Thunk.chain(thunk: Thunk): Thunk.Suspendable<Unit> = ThunkChain(this.unwrapped() + thunk.unwrapped())

private fun Thunk.unwrapped(): List<Thunk> = if (this is ThunkChain) thunks else listOf(this)