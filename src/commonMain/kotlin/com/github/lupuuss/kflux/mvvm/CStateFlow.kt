package com.github.lupuuss.kflux.mvvm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class CStateFlow<out T>(
    state: StateFlow<T>,
    private val scope: CoroutineScope,
) : StateFlow<T> by state {

    private val cFlow = CFlow(state, scope)

    fun collect(onEach: (T) -> Unit, onCompletion: (Throwable?) -> Unit): Disposable = cFlow.collect(onEach, onCompletion)
}