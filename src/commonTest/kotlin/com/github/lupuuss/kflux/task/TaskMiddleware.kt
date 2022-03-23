package com.github.lupuuss.kflux.task

import com.github.lupuuss.kflux.AppState
import com.github.lupuuss.kflux.DateProvider
import com.github.lupuuss.kflux._legacy._Middleware
import com.github.lupuuss.kflux.core.middleware.consumingMiddleware
import kotlinx.datetime.LocalDateTime

fun taskMiddleware(dateProvider: DateProvider) = consumingMiddleware<AppState, TaskAction> { action ->
    when (action) {
        is TaskAction.Complete -> {
            val newTask = state.getTaskById(action.id).copy(isDone = true, modificationDate = dateProvider.get())
            dispatch(TaskStateAction.Replace(newTask))
        }
        is TaskAction.Load -> {
            val tasks = listOf(
                Task("1", "Task 1", false, LocalDateTime(2021, 1, 1, 21, 37)),
                Task("2", "Task 2", false, LocalDateTime(2021, 1, 1, 1, 1)),
                Task("3", "Task 3", false, LocalDateTime(2021, 1, 1, 1, 1)),
            )
            dispatch(TaskStateAction.SetAll(tasks))
        }
    }
}

private fun AppState.getTaskById(id: String) = tasks.first { it.id == id }

@Deprecated("Fujka")
private fun legacyTaskMiddleware(dateProvider: DateProvider): _Middleware<AppState> = { dispatch, getState  ->
    { next ->
        { action ->
            when (action) {
                is TaskAction.Complete -> {
                    val newTask = getState()?.getTaskById(action.id)?.copy(isDone = true, modificationDate = dateProvider.get())
                    if (newTask != null) dispatch(TaskStateAction.Replace(newTask))
                }
                is TaskAction.Load -> {
                    val tasks = listOf(
                        Task("1", "Task 1", false, LocalDateTime(2021, 1, 1, 1, 1)),
                        Task("2", "Task 2", false, LocalDateTime(2021, 1, 1, 1, 1)),
                        Task("3", "Task 3", false, LocalDateTime(2021, 1, 1, 1, 1)),
                    )
                    dispatch(TaskStateAction.SetAll(tasks))
                }
                else -> next(action)
            }
        }
    }
}