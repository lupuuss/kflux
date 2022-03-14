package com.github.lupuuss.kflux.norm

import com.github.lupuuss.kflux.core.scope.DispatchScope
import com.github.lupuuss.kflux.thunk.Thunk
import kotlinx.coroutines.Dispatchers

data class NormalizeEntities<State, Id : Any, Complete : Any, Normalized : Any>(
    val descriptor: EntityDescriptor<State, Id, Complete, Normalized>,
    val entities: List<Complete>
) : Thunk.Suspendable<Unit>(coroutineContext = Dispatchers.Default, nesting = true) {

    override suspend fun DispatchScope<Unit>.executeSuspendable() {
        val normalizedEntities = descriptor.run {
            entities.map { it.normalize() }
        }
        descriptor.storeNormalized(normalizedEntities).let(::dispatch)
        entities.forEach { entity ->
            descriptor.dependencies.forEach {
                it.normalizeExtractingFrom(entity).let(::dispatch)
            }
        }
    }
}