package com.github.lupuuss.kflux.test.tester

import com.github.lupuuss.kflux.core.context.DispatchContext
import com.github.lupuuss.kflux.core.context.element.DispatchCoroutineScope
import com.github.lupuuss.kflux.test.ActionsAssertScope
import com.github.lupuuss.kflux.test.TestDispatchScope
import com.github.lupuuss.kflux.thunk.Thunk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

class ThunkSuspendableTester<State>(
    val scope: TestDispatchScope<State>,
    val sut: Thunk.Suspendable<State>
) {
    suspend fun test(
        state: State = scope.state,
        context: DispatchContext = scope.dispatchContext,
        checks: ActionsAssertScope.() -> Unit
    ) = scope
        .let { scope.cloned(state, context) }
        .run {
            sut.run { executeSuspendable() }
            checks()
        }

    @ExperimentalCoroutinesApi
    fun coroutineTest(
        state: State = scope.state,
        context: DispatchContext = scope.dispatchContext,
        checks: ActionsAssertScope.() -> Unit
    ) {
        runTest {
            test(state, context + DispatchCoroutineScope(this), checks)
        }
    }
}