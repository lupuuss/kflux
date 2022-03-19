package com.github.lupuuss.kflux.test

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.context.EmptyDispatchContext
import com.github.lupuuss.kflux.core.store.Store
import kotlinx.coroutines.flow.MutableStateFlow

interface TestStore<State> : Store<State> {
    override val state: MutableStateFlow<State>

    fun assertions(block: ActionsAssertScope.() -> Unit)
}

fun <State> testStore(state: State): TestStore<State> = TestStoreImpl(state)

internal class TestStoreImpl<State>(state: State) : TestStore<State> {

    private val testScope = DefaultTestDispatchScope(Unit, EmptyDispatchContext)

    override fun dispatch(action: Action) = testScope.dispatch(action)

    override val state: MutableStateFlow<State> = MutableStateFlow(state)

    override fun assertions(block: ActionsAssertScope.() -> Unit) {
        testScope.block()
    }
}