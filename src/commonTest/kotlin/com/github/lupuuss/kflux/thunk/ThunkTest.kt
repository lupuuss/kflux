@file:OptIn(ExperimentalCoroutinesApi::class)
package com.github.lupuuss.kflux.thunk

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.context.element.DispatchCoroutineScope
import com.github.lupuuss.kflux.core.middleware.Middleware
import com.github.lupuuss.kflux.core.middleware.consumingMiddleware
import com.github.lupuuss.kflux.core.scope.DispatchScope
import com.github.lupuuss.kflux.core.scope.consume
import com.github.lupuuss.kflux.core.scope.pass
import com.github.lupuuss.kflux.core.store.builder.buildStore
import com.github.lupuuss.kflux.log.logMiddleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

data class AppState(
    val username: String = "John",
    val age: Int = 15
)

sealed class AgeAction : Action {
    object Inc : AgeAction()
    object Dec : AgeAction()
}

fun appReducer(appState: AppState, action: Action) = with(appState) {
    AppState(
        username = username,
        age = ageReducer(age, action)
    )
}

fun ageReducer(age: Int, action: Action) = when (action) {
    is AgeAction.Inc -> age + 1
    is AgeAction.Dec -> age - 1
    else -> age
}

fun ageMiddleware() = consumingMiddleware<AgeAction, AppState> {
    when (it) {
        AgeAction.Dec -> println("Dec")
        AgeAction.Inc -> println("Inc")
    }
}

class AgeMiddleware : Middleware<AppState> {
    override fun DispatchScope<AppState>.process(action: Action): Middleware.Status {
        if (action !is AgeAction) return pass()
        when (action) {
            AgeAction.Dec -> println("Dec")
            AgeAction.Inc -> println("Inc")
        }
        return consume()
    }

}

class ThunkTest {
    @Test
    fun lol() {
        val job = Job()
        val coScope = CoroutineScope(Dispatchers.Default + job)
        val store = buildStore {
            AppState() reducedBy ::appReducer
            context {
                +DispatchCoroutineScope(coScope)
            }
            middlewares {
                +logMiddleware<AppState>()
            }
        }

        store.dispatch(AgeAction.Inc)

        runTest { job.join() }
    }
}