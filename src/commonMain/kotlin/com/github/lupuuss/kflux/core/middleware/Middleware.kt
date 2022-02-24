package com.github.lupuuss.kflux.core.middleware

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.scope.DispatchScope

fun interface Middleware<State : Any> {

    fun DispatchScope<State>.process(action: Action): Status

    enum class Status {
        Consumed, Passed
    }
}

