package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.task.TaskAction
import com.github.lupuuss.kflux.thunk.thunkSuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AppStoreTest {

    @Test
    fun test() {
        val store = appStore()
        store.dispatch(TaskAction.Load)
        store.dispatch(thunkSuspend<AppState> {
            delay(2000)
            println("-----------------")
            println(state)
        })
    }
}