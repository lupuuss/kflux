package com.github.lupuuss.kflux.combine

import com.github.lupuuss.kflux.core.Action

operator fun Action.plus(action: Action) = CombinedAction(this.unwrapped() + action.unwrapped())

private inline fun Action.unwrapped() = if (this is CombinedAction) actions else listOf(this)