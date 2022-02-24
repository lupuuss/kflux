@file:Suppress("unused")

package com.github.lupuuss.kflux.core.scope

import com.github.lupuuss.kflux.core.Dispatcher
import com.github.lupuuss.kflux.core.context.DispatchContext
import com.github.lupuuss.kflux.core.middleware.Middleware

interface DispatchScope<State : Any> : Dispatcher {
    val dispatchContext: DispatchContext

    val state: State
}

inline fun DispatchScope<*>.consume() = Middleware.Status.Consumed

inline fun DispatchScope<*>.pass() = Middleware.Status.Passed