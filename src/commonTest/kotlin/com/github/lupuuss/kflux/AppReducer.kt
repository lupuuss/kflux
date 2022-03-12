package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.task.taskReducer

fun appReducer(state: AppState, action: Action) = state.run {
    AppState(
        tasks = taskReducer(tasks, action)
    )
}