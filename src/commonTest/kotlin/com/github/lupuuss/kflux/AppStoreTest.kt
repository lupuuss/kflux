package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.task.TaskAction
import com.github.lupuuss.kflux.thunk.thunkSuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AppStoreTest {

    @Test
    fun test() = runTest {
        val store = appStore(this)
        store.dispatch(TaskAction.Load)
        advanceUntilIdle()
        val anonymousThunk = thunkSuspend<AppState> {
            delay(1000)
            println("-----------------")
            println(state)
        }
        store.dispatch(anonymousThunk)
    }
}