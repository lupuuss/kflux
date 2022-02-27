package com.github.lupuuss.kflux.test.assert

import com.github.lupuuss.kflux.core.Action
import com.github.lupuuss.kflux.test.ActionsAssertScope
import kotlin.test.assertTrue
import kotlin.test.fail


fun ActionsAssertScope.expectSingle(
    message: String? = "Given single action doesn't satisfy predicate! $actionsStatus",
    predicate: (Action) -> Boolean
): Action {
    assertTrue(actions.size == 1, "Single action expected! $actionsStatus")
    return actions.first().also { assertTrue(predicate(it), message) }
}

inline fun <reified T : Action> ActionsAssertScope.expectSingleOfType(noinline checks: (T.() -> Unit)? = null) {
    val action = expectSingle("Expected single action of type ${T::class.simpleName}! $actions") { it is T }
    checks ?: return
    checks(action as T)
}

fun ActionsAssertScope.expectAny(
    message: String? = "Expected any action that matches given predicate, but none were found. $actionsStatus",
    predicate: (Action) -> Boolean
): Action {
    return actions.find(predicate) ?: fail(message)
}

inline fun <reified T : Action> ActionsAssertScope.expectAnyOfType(noinline checks: (T.() -> Unit)? = null) {
    val action = expectAny("Expected any action of type ${T::class.simpleName}! $actionsStatus") { it is T }
    checks ?: return
    checks(action as T)
}

fun ActionsAssertScope.expectNone() {
    assertTrue(actions.isEmpty(), "Expected no emissions! $actionsStatus")
}

fun ActionsAssertScope.expectTimeline(block: TimelineScope.() -> Unit) {
    Timeline(actions).apply {
        if (actions.isEmpty()) fail("Timeline requires at least one action! $timelineStatus")
        block()
        if (current != null) fail("You have to process all actions! $timelineStatus")
    }
}

val ActionsAssertScope.actionsStatus get() = "Actual actions >> Count: ${actions.size}; Values: $actions;"