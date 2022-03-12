package com.github.lupuuss.kflux.core

interface ActionDispatcher {
    fun dispatch(action: Action)
}

inline fun ActionDispatcher.dispatchIfPresent(action: Action?) = action?.let(::dispatch)