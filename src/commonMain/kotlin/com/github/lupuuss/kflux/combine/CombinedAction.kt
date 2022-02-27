package com.github.lupuuss.kflux.combine

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.scope.DispatchScope
import com.github.lupuuss.kflux.thunk.Thunk

class CombinedAction(val actions: List<Action>) : Thunk.CoreExecutable {
    override fun DispatchScope<*>.execute() = actions.forEach(this::dispatch)
}