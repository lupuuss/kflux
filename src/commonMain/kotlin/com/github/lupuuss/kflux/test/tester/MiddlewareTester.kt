package com.github.lupuuss.kflux.test.tester

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.context.DispatchContext
import com.github.lupuuss.kflux.core.middleware.Middleware
import com.github.lupuuss.kflux.test.ActionsAssertScope
import com.github.lupuuss.kflux.test.TestDispatchScope

class MiddlewareTester<State>(
    val scope: TestDispatchScope<State>,
    private val middleware: Middleware<State>
) {

    fun test(
        action: Action,
        state: State = scope.state,
        context: DispatchContext = scope.dispatchContext,
        checks: ActionsAssertScope.() -> Unit
    ) =  scope
        .let { scope.cloned(state, context) }
        .run {
            middleware.run { process(action) }
            checks()
        }
}