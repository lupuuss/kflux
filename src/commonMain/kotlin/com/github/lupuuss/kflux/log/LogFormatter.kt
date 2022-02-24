package com.github.lupuuss.kflux.log

import com.github.lupuuss.kflux.core.Action

fun interface LogFormatter {
    fun format(action: Action): String

    companion object {
        fun withToString(tag: String? = null) = LogFormatter { "${tag.orEmpty()}$it"}
    }
}