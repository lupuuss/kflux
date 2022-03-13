package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.kodein.KodeinDI
import com.github.lupuuss.kflux.task.Task
import com.github.lupuuss.kflux.task.TaskAction
import com.github.lupuuss.kflux.task.TaskStateAction
import com.github.lupuuss.kflux.task.taskMiddleware
import com.github.lupuuss.kflux.test.assert.*
import com.github.lupuuss.kflux.test.tester.tester
import kotlinx.datetime.LocalDateTime
import org.kodein.di.bindSingleton
import kotlin.test.Test

class TaskMiddlewareTest {
    private val stubDate = LocalDateTime(2023, 1, 1, 1, 1, 1)
    private val context = KodeinDI {
        bindSingleton { DateProvider { stubDate } }
    }
    private val tasks = listOf(
        Task(
            "1",
            "Task 1",
            false,
            LocalDateTime(2022, 1, 1, 1, 1, 1)
        )
    )
    private val tester = taskMiddleware().tester(AppState(tasks = tasks), context)

    @Test
    fun test() = tester.test(TaskAction.Complete("1")) {
        val task = Task("1", "Task 1", true, stubDate)
        expectSingleEquals(TaskStateAction.Replace(task))
    }
}