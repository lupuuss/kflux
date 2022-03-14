package com.github.lupuuss.kflux.thunk

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.context.DispatchContext
import com.github.lupuuss.kflux.core.context.element.DispatchCoroutineScope
import com.github.lupuuss.kflux.core.scope.DispatchScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class CoroutineNestingDispatchScope<State>(
    private val outerCoroutine: CoroutineScope,
    private val outerDispatch: DispatchScope<State>,
    private val nesting: Boolean
) : DispatchScope<State> {
    override fun dispatch(action: Action) {
        if (nesting && action is Thunk.Suspendable<*>) outerCoroutine.launch(action.coroutineContext, action.coroutineStart) {
            handleNesting(action.cast(), this@CoroutineNestingDispatchScope, this)
        } else {
            outerDispatch.dispatch(action)
        }
    }

    override val dispatchContext: DispatchContext
        get() = outerDispatch.dispatchContext + DispatchCoroutineScope(outerCoroutine)

    override val state: State get() = outerDispatch.state
}

internal suspend inline fun <State> handleNesting(
    thunk: Thunk.Suspendable<State>,
    dispatchScope: DispatchScope<State>,
    coroutineScope: CoroutineScope,
) {
    val scope = if (thunk.nesting) {
        CoroutineNestingDispatchScope(coroutineScope, dispatchScope, thunk.nesting)
    } else {
        dispatchScope
    }
    with(scope) {
        thunk.cast<State>().run { executeSuspendable() }
    }
}