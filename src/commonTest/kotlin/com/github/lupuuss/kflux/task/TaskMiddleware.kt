package com.github.lupuuss.kflux.task

import com.github.lupuuss.kflux.AppState
import com.github.lupuuss.kflux.DateProvider
import com.github.lupuuss.kflux.closure.middlewareClosure
import com.github.lupuuss.kflux.core.middleware.consumingMiddleware
import com.github.lupuuss.kflux.kodein.di
import kotlinx.datetime.LocalDateTime
import org.kodein.di.instance

fun taskMiddleware() = middlewareClosure {
    val dateProvider by di.instance<DateProvider>()
    println("CLOSURE INIT")
    consumingMiddleware<TaskAction, AppState> { action ->
        when (action) {
            is TaskAction.Complete -> {
                val newTask = state.getTaskById(action.id).copy(isDone = true, modificationDate = dateProvider.get())
                dispatch(TaskStateAction.Replace(newTask))
            }
            is TaskAction.Load -> {
                val tasks = listOf(
                    Task("1", "Task 1", false, LocalDateTime(2021, 1, 1, 1, 1)),
                    Task("2", "Task 2", false, LocalDateTime(2021, 1, 1, 1, 1)),
                    Task("3", "Task 3", false, LocalDateTime(2021, 1, 1, 1, 1)),
                )
                dispatch(TaskStateAction.SetAll(tasks))
            }
        }
    }
}

private fun AppState.getTaskById(id: String) = tasks.first { it.id == id }
