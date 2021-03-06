package com.github.lupuuss.kflux.core.store.builder

import com.github.lupuuss.kflux.core.middleware.Middleware

interface MiddlewaresBuilderScope<State> {
    operator fun Middleware<State>.unaryPlus()
}

internal class MiddlewaresBuilder<State> : MiddlewaresBuilderScope<State> {
    val middlewares = mutableListOf<Middleware<State>>()

    override fun Middleware<State>.unaryPlus() {
        middlewares.add(this)
    }
}