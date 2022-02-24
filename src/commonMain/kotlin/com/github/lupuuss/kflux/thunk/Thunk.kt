package com.github.lupuuss.kflux.thunk

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.scope.DispatchScope
import kotlinx.coroutines.CoroutineStart
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Suppress("UNCHECKED_CAST")
sealed interface Thunk : Action {

    sealed interface CoreExecutable : Thunk {
        fun DispatchScope<*>.execute()
    }

    sealed interface CoreSuspendable : Thunk {
        val coroutineStart: CoroutineStart
        val coroutineContext: CoroutineContext

        suspend fun DispatchScope<*>.executeSuspendable()
    }

    abstract class Executable<State : Any> : CoreExecutable {

        final override fun DispatchScope<*>.execute() = (this as DispatchScope<State>).onExecute()

        abstract fun DispatchScope<State>.onExecute()
    }

    abstract class Suspendable<State : Any>(
        override val coroutineContext: CoroutineContext = EmptyCoroutineContext,
        override val coroutineStart: CoroutineStart = CoroutineStart.DEFAULT
    ) : CoreSuspendable {

        final override suspend fun DispatchScope<*>.executeSuspendable() = (this as DispatchScope<State>).onExecuteSuspendable()

        abstract suspend fun DispatchScope<State>.onExecuteSuspendable()
    }
}