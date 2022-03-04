package com.github.lupuuss.kflux.log

import com.github.lupuuss.kflux.core.middleware.translucentMiddleware
import com.github.lupuuss.kflux.thunk.Thunk

fun <State> logMiddleware(
    filter: LogFilter = LogFilter.all(),
    formatter: LogFormatter = LogFormatter.withToString("KFlux-Log >> "),
    adapter: LogBusAdapter = LogBusAdapter.systemOut(),
) = translucentMiddleware<State> { action ->
    if (filter.filter(action)) adapter.log(formatter.format(action))
}