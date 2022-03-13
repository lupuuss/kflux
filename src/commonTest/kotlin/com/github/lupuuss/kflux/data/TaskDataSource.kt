package com.github.lupuuss.kflux.data

import com.github.lupuuss.kflux.task.Task
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDateTime

class TaskDataSource : DataSource<Unit, List<Task>> {
    override suspend fun get(request: Unit): List<Task> {
        delay(1000)
        return listOf(
            Task("1", "Task 1", false, LocalDateTime(2021, 1, 1, 1, 1)),
            Task("2", "Task 2", false, LocalDateTime(2021, 1, 1, 1, 1)),
            Task("3", "Task 3", false, LocalDateTime(2021, 1, 1, 1, 1)),
        )
    }
}