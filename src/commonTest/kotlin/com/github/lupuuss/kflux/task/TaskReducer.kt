package com.github.lupuuss.kflux.task

import com.github.lupuuss.kflux.core.Action

fun taskReducer(tasks: List<Task>, action: Action) = when(action) {
    is TaskStateAction.SetAll -> action.tasks
    is TaskStateAction.Replace -> tasks.map { if (it.id == action.task.id) action.task else it }
    else -> tasks
}