package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.task.TaskAction
import com.github.lupuuss.kflux.thunk.thunkSuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AppStoreTest {

    @Test
    fun test() = runTest {
        val store = appStore(this)
        store.dispatch(TaskAction.Load)
        store.dispatch(thunkSuspend<AppState> {
            delay(2000)
            println("-----------------")
            println(state)
        })
    }
}