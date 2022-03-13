package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.combine.plus
import com.github.lupuuss.kflux.data.DataSourceFactory
import com.github.lupuuss.kflux.data.TaskDataSource
import com.github.lupuuss.kflux.kodein.KodeinDI
import com.github.lupuuss.kflux.task.Task
import com.github.lupuuss.kflux.task.TaskAction
import com.github.lupuuss.kflux.task.TaskStateAction
import com.github.lupuuss.kflux.test.assert.expectSingleEquals
import com.github.lupuuss.kflux.test.tester.coroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.datetime.LocalDateTime
import org.kodein.di.bindSingleton
import kotlin.test.Test

@ExperimentalCoroutinesApi
class TaskActionTest {

    private val testTask = Task("1", "Task 1", false, LocalDateTime(2020, 1, 1, 1, 1, 1))

    private val context = KodeinDI {
        bindSingleton<DataSourceFactory> {
            object : DataSourceFactory {
                override fun tasks() = TaskDataSource(listOf(testTask))
            }
        }
    }

    @Test
    fun test() = TaskAction.Load.coroutineTest(AppState(), context) {
        expectSingleEquals(TaskStateAction.SetAll(listOf(testTask)) + TaskAction.Complete("1") )
    }
}