package com.github.lupuuss.kflux.core.scope

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.context.DispatchContext

class CoreDispatchScope<State>(
    override val dispatchContext: DispatchContext,
    private val dispatchFunction: (Action) -> Unit,
    private val getState: () -> State,
) : DispatchScope<State> {

    override val state: State get() = getState()

    override fun dispatch(action: Action) = dispatchFunction(action)
}