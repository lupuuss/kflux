package com.github.lupuuss.kflux.flavor

import com.github.lupuuss.kflux.core.store.builder.*
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
fun <Flavor : StoreFlavor, State : Any> buildFlavoredStore(
    flavor: Flavor,
    @BuilderInference block: FlavoredStoreBuilderScope<Flavor, State>.() -> Unit
) = buildStore {
    context { +StoreFlavor.Current(flavor) }
    FlavoredStoreBuilder(this, flavor).apply(block)
}

interface FlavoredStoreBuilderScope<Flavor : StoreFlavor, State : Any> : StoreBuilderScope<State> {
    val flavor: Flavor
}

internal class FlavoredStoreBuilder<Flavor : StoreFlavor, State : Any>(
    outerStoreBuilder: StoreBuilderScope<State>,
    override val flavor: Flavor,
) : FlavoredStoreBuilderScope<Flavor, State>, StoreBuilderScope<State> by outerStoreBuilder