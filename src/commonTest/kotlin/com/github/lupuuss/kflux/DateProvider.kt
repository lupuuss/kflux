package com.github.lupuuss.kflux

import kotlinx.datetime.LocalDateTime

fun interface DateProvider {
    fun get(): LocalDateTime
}