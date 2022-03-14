package com.github.lupuuss.kflux.thunk

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.scope.DispatchScope
import kotlinx.coroutines.CoroutineStart
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Suppress("UNCHECKED_CAST")
sealed interface Thunk : Action {

    interface Executable<State> : Thunk {
        fun DispatchScope<State>.execute()
    }

    abstract class Suspendable<State>(
        val coroutineContext: CoroutineContext = EmptyCoroutineContext,
        val coroutineStart: CoroutineStart = CoroutineStart.DEFAULT,
        val nesting: Boolean = false,
    ) : Thunk {
        abstract suspend fun DispatchScope<State>.executeSuspendable()
    }

    open class Execute<State>(private val block: DispatchScope<State>.() -> Unit) : Executable<State> {
        override fun DispatchScope<State>.execute() = block()
    }

    open class Suspend<State>(
        private val block: suspend  DispatchScope<State>.() -> Unit,
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
        coroutineStart: CoroutineStart = CoroutineStart.DEFAULT,
        nesting: Boolean = false,
    ) : Suspendable<State>(coroutineContext, coroutineStart, nesting) {
        override suspend fun DispatchScope<State>.executeSuspendable() = block()
    }
}