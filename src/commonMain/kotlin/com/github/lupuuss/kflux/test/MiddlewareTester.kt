package com.github.lupuuss.kflux.test

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.context.DispatchContext
import com.github.lupuuss.kflux.core.context.EmptyDispatchContext
import com.github.lupuuss.kflux.core.middleware.Middleware

class MiddlewareTester<State>(
    val scope: TestDispatchScope<State>,
    val sut: Middleware<State>
) {

    fun test(
        action: Action,
        context: DispatchContext = EmptyDispatchContext,
        checks: ActionsAssertScope.() -> Unit
    ) = scope
            .let { if (context != EmptyDispatchContext) it.swapContext(context) else it }
            .run {
                sut.run { process(action) }
                checks()
            }
}