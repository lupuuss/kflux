package com.github.lupuuss.kflux.mvvm

import com.github.lupuuss.kflux.core.store.Store
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<State>(
    protected val store: Store<State>,
    protected val coroutineScope: CoroutineScope
) {
    protected fun <T, R> StateFlow<T>.mapState(transform: (T) -> R): StateFlow<R> = map(transform)
        .stateInHere(initialValue = transform(value))

    protected fun <T> Flow<T>.stateInHere(
        started: SharingStarted = SharingStarted.WhileSubscribed(),
        initialValue: T
    ) = stateIn(coroutineScope, started, initialValue)

    protected fun <T> Flow<T>.stateInHere(started: SharingStarted = SharingStarted.WhileSubscribed()) =
        stateIn(coroutineScope, started, null)

    protected fun <T> Flow<T>.common() = CFlow(this, coroutineScope)

    protected fun <T> StateFlow<T>.common() = CStateFlow(this, coroutineScope)
}