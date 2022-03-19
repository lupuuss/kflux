package com.github.lupuuss.kflux.mvvm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class CFlow<out T>(
    flow: Flow<T>,
    private val scope: CoroutineScope,
) : Flow<T> by flow {

    fun collect(onEach: (T) -> Unit, onCompletion: (Throwable?) -> Unit): Disposable = this.onEach(onEach)
        .onCompletion { onCompletion(it) }
        .catch { }
        .launchIn(scope)
        .let { Disposable(it::cancel) }

}