package com.github.lupuuss.kflux

import com.github.lupuuss.kflux.core.context.DispatchContext

private class AppName(val value: String) : DispatchContext.Element {

    override val key = Key

    companion object Key : DispatchContext.Key<AppName>
}

private class ExternalServiceId(val serviceId: Any) : DispatchContext.Element {

    override val key = Key

    companion object Key : DispatchContext.Key<ExternalServiceId>
}

private val context1: DispatchContext = AppName("1234")

private val context2: DispatchContext = AppName("4321") + ExternalServiceId(1)


fun example() {

    context1[AppName].value // "1234"

    context2[AppName].value // "4321"

    context2[ExternalServiceId].serviceId // 1
}