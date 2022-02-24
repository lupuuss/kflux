package com.github.lupuuss.kflux.log

fun interface LogBusAdapter {

    fun log(message: String)

    companion object {
        fun systemOut() = LogBusAdapter { println(it) }
    }
}