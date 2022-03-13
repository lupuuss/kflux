package com.github.lupuuss.kflux.data

import com.github.lupuuss.kflux.task.Task
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDateTime

class TaskDataSource(
    private val tasks: List<Task> = default
) : DataSource<Unit, List<Task>> {
    override suspend fun get(request: Unit): List<Task> {
        delay(1000)
        return tasks
    }

    companion object {
        private val default = listOf(
            Task("1", "Task 1", false, LocalDateTime(2021, 1, 1, 1, 1)),
            Task("2", "Task 2", false, LocalDateTime(2021, 1, 1, 1, 1)),
            Task("3", "Task 3", false, LocalDateTime(2021, 1, 1, 1, 1)),
        )
    }
}