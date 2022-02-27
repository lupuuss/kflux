package com.github.lupuuss.kflux.core.store

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.Reducer
import com.github.lupuuss.kflux.core.middleware.*
import com.github.lupuuss.kflux.core.context.EmptyDispatchContext
import com.github.lupuuss.kflux.core.context.DispatchContext
import com.github.lupuuss.kflux.core.scope.CoreDispatchScope
import kotlinx.coroutines.flow.MutableStateFlow

internal class StoreImpl<State : Any>(
    initialState: State,
    private val reducer: Reducer<State>,
    private val middlewares: List<Middleware<State>> = emptyList(),
    context: DispatchContext = EmptyDispatchContext
) : Store<State> {
    override val state = MutableStateFlow(initialState)

    private val scope = CoreDispatchScope(context, this::dispatch, this.state::value)

    override fun dispatch(action: Action) {
        var status = Middleware.Status.Passed
        for (middleware in middlewares) {
            status = middleware.run { scope.process(action) }
            if (status == Middleware.Status.Consumed) break
        }
        if (status == Middleware.Status.Passed) state.value = reducer(state.value, action)
    }
}