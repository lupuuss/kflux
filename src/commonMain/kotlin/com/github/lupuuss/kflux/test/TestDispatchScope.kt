package com.github.lupuuss.kflux.test

import com.github.lupuuss.kflux.core.context.DispatchContext
import com.github.lupuuss.kflux.core.scope.DispatchScope

interface TestDispatchScope<State> : DispatchScope<State>, ActionsAssertScope {
    fun swapContext(context: DispatchContext): TestDispatchScope<State>
}