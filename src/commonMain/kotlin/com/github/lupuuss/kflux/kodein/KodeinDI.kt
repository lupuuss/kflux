package com.github.lupuuss.kflux.kodein

import com.github.lupuuss.kflux.core.context.DispatchContext
import com.github.lupuuss.kflux.core.scope.DispatchScope
import org.kodein.di.DI

class KodeinDI(di: DI): DI by di, DispatchContext.Element {
    override val key = Key

    companion object Key : DispatchContext.Key<KodeinDI>
}

val DispatchScope<*>.di: DI get() = dispatchContext[KodeinDI]