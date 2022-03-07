package com.github.lupuuss.kflux.log

import com.github.lupuuss.kflux.core.Action

fun interface LogBusAdapter {

    fun log(action: Action, message: String)

    companion object {
        fun systemOut() = LogBusAdapter { _, msg -> println(msg) }
    }
}