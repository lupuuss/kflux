package com.github.lupuuss.kflux.test.assert

import com.github.lupuuss.kflux.core.Action
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class Timeline(override val actions: List<Action>) : TimelineScope {

    private val iterator = actions.listIterator()
    override var current: Action? = iterator.nextOrNull()
    override val timelineStatus: String
        get() = timelineStatus()

    override fun actionThat(message: String?, predicate: (Action) -> Boolean) {
        val current = current
        assertNotNull(current, "Unexpected end of timeline! $timelineStatus")
        assertTrue(predicate(current), message ?: "Action $current doesn't match the predicate! $timelineStatus")
        processNextAction()
    }

    override fun skip(count: Int) {
        repeat(count) { processNextAction() }
    }

    private fun processNextAction() {
        current = iterator.nextOrNull()
    }

    private fun timelineStatus(): String {
        if (actions.isEmpty()) return "Empty"
        if (current == null) return "End of timeline! $actions"
        return "Failed at marked action! [\n" + actions
            .mapIndexed { index, action -> if (index == iterator.currentIndex()) "==> $action" else "\t$action"}
            .joinToString("\n") { "\t$it" } + "\n]"
    }


    private fun <T> ListIterator<T>.nextOrNull(): T? = if (hasNext()) next() else null

    private fun <T> ListIterator<T>.currentIndex(): Int = nextIndex() - 1
}