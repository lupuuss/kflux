package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.core.middleware.translucentMiddleware

fun debugMiddleware() = translucentMiddleware<AppState> {
    println(">>>> $it\n")
}