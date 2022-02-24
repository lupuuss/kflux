package com.github.lupuuss.kflux.flavor

import com.github.lupuuss.kflux.core.context.DispatchContext
import com.github.lupuuss.kflux.core.scope.DispatchScope

interface StoreFlavor {

    class Current(val value: StoreFlavor) : DispatchContext.Element {
        override val key = Current

        companion object Key : DispatchContext.Key<Current>
    }
}

inline fun <reified T : StoreFlavor> DispatchScope<*>.flavor() = dispatchContext[StoreFlavor.Current].value as T