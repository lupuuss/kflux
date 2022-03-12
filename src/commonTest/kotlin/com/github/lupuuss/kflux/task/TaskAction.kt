package com.github.lupuuss.kflux.task

import com.github.lupuuss.kflux.core.Action

sealed class TaskAction : Action {

    data class Complete(val id: String) : TaskAction()

    object Load : TaskAction()
}