package com.github.lupuuss.kflux.core.store.builder

import com.github.lupuuss.kflux.core.Reducer
import com.github.lupuuss.kflux.core.context.DispatchContext
import com.github.lupuuss.kflux.core.store.Store
import com.github.lupuuss.kflux.core.store.StoreImpl
import kotlin.experimental.ExperimentalTypeInference

@DslMarker
annotation class BuildStoreDsl

@OptIn(ExperimentalTypeInference::class)
fun <State : Any> buildStore(@BuilderInference block: StoreBuilderScope<State>.() -> Unit): Store<State> {
    val builder = StoreBuilder<State>()
    builder.block()
    return builder.build()
}

interface StoreBuilderScope<State : Any> {

    @BuildStoreDsl
    fun middlewares(block: MiddlewaresBuilderScope<State>.() -> Unit)

    @BuildStoreDsl
    fun context(block: ContextBuilderScope.() -> Unit)

    @BuildStoreDsl
    infix fun State.reducedBy(reducer: Reducer<State>)
}

internal class StoreBuilder<State : Any> : StoreBuilderScope<State> {

    private val middlewareBuilder = MiddlewaresBuilder<State>()
    private val contextBuilder = ContextBuilder()
    private var state: State? = null
    private var stateReducer: Reducer<State>? = null

    override infix fun State.reducedBy(reducer: Reducer<State>) {
        state = this
        stateReducer = reducer
    }

    override fun middlewares(block: MiddlewaresBuilderScope<State>.() -> Unit) {
        middlewareBuilder.apply(block)
    }

    override fun context(block: ContextBuilderScope.() -> Unit) {
        contextBuilder.apply(block)
    }

    fun build() = StoreImpl(
        requireNotNull(state) { stateAndReducerMissingMsg() },
        requireNotNull(stateReducer) { stateAndReducerMissingMsg() },
        middlewareBuilder.middlewares,
        contextBuilder.context
    )

    private fun stateAndReducerMissingMsg() = "Main state and reducer are missing!"
}