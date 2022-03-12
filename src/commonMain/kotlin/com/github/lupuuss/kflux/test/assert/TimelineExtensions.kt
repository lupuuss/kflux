package com.github.lupuuss.kflux.test.assert

import com.github.lupuuss.kflux.core.Action

inline fun <reified T : Action> TimelineScope.actionOfType(crossinline checks: (T) -> Unit) {
    actionThat { (it as? T)?.also(checks) != null }
}

fun TimelineScope.skip(count: Int) {
    var counter = 0
    skipWhile { ++counter != count }
}

fun TimelineScope.skipOthers() {
    skipWhile { it != null }
}