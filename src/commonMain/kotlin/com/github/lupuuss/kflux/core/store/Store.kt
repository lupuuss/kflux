package com.github.lupuuss.kflux.core.store

import com.github.lupuuss.kflux.core.ActionDispatcher
import kotlinx.coroutines.flow.StateFlow

interface Store<State> : ActionDispatcher {

    val state: StateFlow<State>
}

inline val <State> Store<State>.currentState get() = state.value