package com.github.lupuuss.kflux.core.store

import com.github.lupuuss.kflux.core.Dispatcher
import kotlinx.coroutines.flow.StateFlow

interface Store<State : Any> : Dispatcher {

    val state: StateFlow<State>
}