package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.core.middleware.Middleware
import com.github.lupuuss.kflux.core.middleware.consume
import com.github.lupuuss.kflux.core.middleware.pass

private sealed class CustomAction : Action {

    object A : CustomAction()
    object B : CustomAction()
    object C : CustomAction()
}

private fun customMiddleware() = Middleware<AppState> { action ->
    when (action) {
        is CustomAction.A, is CustomAction.B -> {
            println("PASS $action")
            pass()
        }
        is CustomAction.C -> {
            println(state)
            dispatch(CustomAction.B)
            consume()
        }
        else -> pass()
    }
}