package com.github.lupuuss.kflux.data

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.scope.DispatchScope
import kotlinx.coroutines.CoroutineStart
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun <Data, State> dataLoader(
    onSuccess: (Data) -> Action,
    onError: (Throwable) -> Action,
    onStart: () -> Action? = { null },
    onFinish: () -> Action? = { null },
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    coroutineStart: CoroutineStart = CoroutineStart.DEFAULT,
    loader: suspend DispatchScope<State>.() -> Data,
) = object : DataLoader<Data, State>(coroutineContext, coroutineStart) {

    override suspend fun DispatchScope<State>.load(): Data = loader()

    override fun onSuccess(data: Data) = onSuccess(data)

    override fun onError(error: Throwable) = onError(error)

    override fun onStart(): Action? = onStart()

    override fun onFinish(): Action? = onFinish()
}