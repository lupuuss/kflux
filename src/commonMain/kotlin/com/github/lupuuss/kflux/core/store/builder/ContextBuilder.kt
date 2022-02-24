package com.github.lupuuss.kflux.core.store.builder

import com.github.lupuuss.kflux.core.context.DispatchContext
import com.github.lupuuss.kflux.core.context.EmptyDispatchContext


interface ContextBuilderScope {
    operator fun DispatchContext.unaryPlus()
}

internal class ContextBuilder : ContextBuilderScope {
    var context: DispatchContext = EmptyDispatchContext

    override operator fun DispatchContext.unaryPlus() {
        context += this
    }
}