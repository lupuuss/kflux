package com.github.lupuuss.kflux.test

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.context.DispatchContext

data class DefaultTestDispatchScope<State>(
    override val state: State,
    override val dispatchContext: DispatchContext,
) : TestDispatchScope<State> {

    override val actions = mutableListOf<Action>()

    override fun dispatch(action: Action) {
        actions.add(action)
    }

    override fun cloned(state: State, context: DispatchContext) = copy(state = state, dispatchContext = context)
}