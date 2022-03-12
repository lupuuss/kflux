@file:Suppress("unused")

package com.github.lupuuss.kflux.core.scope

import com.github.lupuuss.kflux.core.ActionDispatcher
import com.github.lupuuss.kflux.core.context.DispatchContext

interface DispatchScope<State> : ActionDispatcher {
    val dispatchContext: DispatchContext

    val state: State
}
