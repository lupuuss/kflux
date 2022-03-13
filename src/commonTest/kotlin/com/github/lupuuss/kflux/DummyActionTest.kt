package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.dummy.DummyAction
import com.github.lupuuss.kflux.test.assert.*
import com.github.lupuuss.kflux.test.tester.coroutineTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DummyActionTest {

    @Test
    fun test0() = DummyAction.Process.coroutineTest(AppState()) {
        expectTimeline {
            actionEquals(DummyAction.A)
            actionEquals(DummyAction.A)
        }
    }

    @Test
    fun test1() = DummyAction.Process.coroutineTest(AppState()) {
        expectTimeline {
            actionEquals(DummyAction.A)
            actionEquals(DummyAction.B)
            actionEquals(DummyAction.C)
        }
    }

    @Test
    fun test2() = DummyAction.Process.coroutineTest(AppState()) {
        expectTimeline {
            actionEquals(DummyAction.A)
            skip(1)
            actionEquals(DummyAction.C)
        }
    }

    @Test
    fun test3() = DummyAction.Process.coroutineTest(AppState()) {
        expectSingleEquals(DummyAction.A)
    }

    @Test
    fun test4() = DummyAction.Process.coroutineTest(AppState()) {
        expectAnyEquals(DummyAction.A)
    }
}