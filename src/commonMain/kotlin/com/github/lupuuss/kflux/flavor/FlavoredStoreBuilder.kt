package com.github.lupuuss.kflux.flavor

import com.github.lupuuss.kflux.core.store.builder.*
import kotlin.experimental.ExperimentalTypeInference

@DslMarker
annotation class FlavorBuilderDslMarker

@OptIn(ExperimentalTypeInference::class)
fun <Flavor : StoreFlavor, State> buildFlavoredStore(
    flavor: Flavor,
    @BuilderInference block: FlavoredStoreBuilderScope<Flavor, State>.() -> Unit
) = buildStore {
    context { +StoreFlavor.Current(flavor) }
    FlavoredStoreBuilder(this, flavor).apply(block)
}

interface FlavoredStoreBuilderScope<Flavor : StoreFlavor, State> : StoreBuilderScope<State> {
    val flavor: Flavor
}

@FlavorBuilderDslMarker
inline fun <reified T : StoreFlavor> FlavoredStoreBuilderScope<*, *>.on(block: (T) -> Unit) {
    (flavor as? T)?.let(block)
}

@FlavorBuilderDslMarker
inline fun <reified T : StoreFlavor> FlavoredStoreBuilderScope<*, *>.on(flavor: T, block: (T) -> Unit) {
    this.flavor
        .takeIf { it == flavor }
        .let { it as? T }
        ?.let(block)
}

internal class FlavoredStoreBuilder<Flavor : StoreFlavor, State>(
    outerStoreBuilder: StoreBuilderScope<State>,
    override val flavor: Flavor,
) : FlavoredStoreBuilderScope<Flavor, State>, StoreBuilderScope<State> by outerStoreBuilder