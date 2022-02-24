package com.github.lupuuss.kflux.core

interface Dispatcher {
    fun dispatch(action: Action)
}

inline fun Dispatcher.dispatchIfPresent(action: Action?) = action?.let(::dispatch)