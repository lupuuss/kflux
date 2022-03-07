package com.github.lupuuss.kflux.data

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.dispatchIfPresent
import com.github.lupuuss.kflux.core.scope.DispatchScope
import com.github.lupuuss.kflux.thunk.Thunk
import kotlinx.coroutines.CoroutineStart
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class DataLoader<Data, State>(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    coroutineStart: CoroutineStart = CoroutineStart.DEFAULT,
) : Thunk.Suspendable<State>(coroutineContext, coroutineStart) {

    abstract suspend fun DispatchScope<State>.load(): Data

    abstract fun onSuccess(data: Data): Action?

    abstract fun onError(error: Throwable): Action?

    open fun onStart(): Action? = null

    open fun onFinish(): Action? = null

    override suspend fun DispatchScope<State>.executeSuspendable() {
        onStart()
        runCatching { load() }
            .also { onFinish() }
            .onSuccess { dispatchIfPresent(onSuccess(it)) }
            .onFailure { dispatchIfPresent(onError(it)) }
    }
}