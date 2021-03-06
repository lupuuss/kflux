package com.github.lupuuss.kflux.core.context.element

import com.github.lupuuss.kflux.core.context.DispatchContext
import com.github.lupuuss.kflux.core.scope.DispatchScope
import kotlinx.coroutines.CoroutineScope

class DispatchCoroutineScope(scope: CoroutineScope) : DispatchContext.Element, CoroutineScope by scope {
    override val key get() = DispatchCoroutineScope

    companion object Key : DispatchContext.Key<DispatchCoroutineScope>
}

val DispatchScope<*>.coroutineScope get() = dispatchContext[DispatchCoroutineScope]