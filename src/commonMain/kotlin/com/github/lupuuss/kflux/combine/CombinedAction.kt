package com.github.lupuuss.kflux.combine

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.scope.DispatchScope
import com.github.lupuuss.kflux.thunk.Thunk

data class CombinedAction(val actions: List<Action>) : Thunk.Executable<Unit> {

    override fun DispatchScope<Unit>.execute() = actions.forEach(this::dispatch)
}