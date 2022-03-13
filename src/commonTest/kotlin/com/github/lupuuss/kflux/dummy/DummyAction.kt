package com.github.lupuuss.kflux.dummy

import com.github.lupuuss.kflux.AppState
import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.thunk.Thunk
import kotlinx.coroutines.delay

object DummyAction {

    object A : Action
    object B : Action
    object C : Action

    object Process : Thunk.Suspend<AppState>({
        dispatch(A)
        delay(100)
        dispatch(B)
        delay(100)
        dispatch(C)
    })
}