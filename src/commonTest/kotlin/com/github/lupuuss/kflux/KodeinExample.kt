package com.github.lupuuss.kflux

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance


private val di: DI = DI {
    bindSingleton { "Dependency" }
}

private class KodeinExample(di: DI) {

    val dependency: String by di.instance()
}