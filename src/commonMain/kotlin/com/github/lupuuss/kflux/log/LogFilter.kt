package com.github.lupuuss.kflux.log

import com.github.lupuuss.kflux.core.Action
import kotlin.reflect.KClass

fun interface LogFilter {
    fun filter(action: Action): Boolean

    companion object {

        fun all() = LogFilter { true }

        inline fun <reified T : Any> ofType() = LogFilter { it is T }

        fun ofTypes(vararg types: KClass<*>) = LogFilter { it::class in types }
    }
}