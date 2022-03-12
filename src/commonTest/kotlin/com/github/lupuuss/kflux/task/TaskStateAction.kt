package com.github.lupuuss.kflux.task

import com.github.lupuuss.kflux.core.Action

sealed class TaskStateAction : Action {

    data class SetAll(val tasks: List<Task>) : TaskStateAction()

    data class Replace(val task: Task) : TaskStateAction()
}