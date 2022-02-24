package com.github.lupuuss.kflux.core.store.builder

import com.github.lupuuss.kflux.core.middleware.Middleware

interface MiddlewaresBuilderScope<State : Any> {
    operator fun Middleware<State>.unaryPlus()
}

internal class MiddlewaresBuilder<State : Any> : MiddlewaresBuilderScope<State> {
    val middlewares = mutableListOf<Middleware<State>>()

    override fun Middleware<State>.unaryPlus() {
        middlewares.add(this)
    }
}