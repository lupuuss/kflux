package com.github.lupuuss.kflux.task

import com.github.lupuuss.kflux.AppState
import com.github.lupuuss.kflux.DateProvider
import com.github.lupuuss.kflux.core.scope.DispatchScope
import com.github.lupuuss.kflux.kodein.di
import com.github.lupuuss.kflux.thunk.Thunk
import kotlinx.datetime.LocalDateTime
import org.kodein.di.instance

sealed class TaskAction : Action {

    data class Complete(val id: String) : Thunk.Executable<AppState> {
        override fun DispatchScope<AppState>.execute() {
            val dateProvider by di.instance<DateProvider>()
            val newTask = state.getTaskById(id).copy(isDone = true, modificationDate = dateProvider.get())
            dispatch(TaskStateAction.Replace(newTask))
        }
    }

    object Load : Thunk.Executable<AppState> {
        override fun DispatchScope<AppState>.execute() {
            val tasks = listOf(
                Task("1", "Task 1", false, LocalDateTime(2021, 1, 1, 1, 1)),
                Task("2", "Task 2", false, LocalDateTime(2021, 1, 1, 1, 1)),
                Task("3", "Task 3", false, LocalDateTime(2021, 1, 1, 1, 1)),
            )
            dispatch(TaskStateAction.SetAll(tasks))
        }
    }
}