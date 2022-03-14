package com.github.lupuuss.kflux.thunk

import com.github.lupuuss.kflux.core.scope.DispatchScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

data class ThunkChain(val thunks: List<Thunk>) : Thunk.Suspendable<Unit>() {
    override suspend fun DispatchScope<Unit>.executeSuspendable() {
        coroutineScope {
            thunks.forEach {
                when(it) {
                    is Thunk.Executable<*> -> it.cast<Unit>().run { execute() }
                    is Thunk.Suspendable<*> -> launch(it.coroutineContext, it.coroutineStart) {
                        handleNesting(it.cast(), this@executeSuspendable, this)
                    }.join()
                }
            }
        }
    }
}