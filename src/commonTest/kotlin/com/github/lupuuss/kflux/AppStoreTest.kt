package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.task.TaskAction
import kotlin.test.Test

class AppStoreTest {

    @Test
    fun test() {
        val store = appStore()
        store.dispatch(object : Action {})
        store.dispatch(TaskAction.Load)
        store.dispatch(TaskAction.Complete("1"))
        println("---------------------------------")
        println(store.state.value)
    }
}