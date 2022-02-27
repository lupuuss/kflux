package com.github.lupuuss.kflux.test.assert

import com.github.lupuuss.kflux.core.Action

interface TimelineScope {
    val actions: List<Action>
    val current: Action?
    val timelineStatus: String

    fun actionThat(message: String? = null, predicate: (Action) -> Boolean)

    fun skip(count: Int)
}