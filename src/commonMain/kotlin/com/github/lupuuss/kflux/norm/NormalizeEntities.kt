package com.github.lupuuss.kflux.norm

import com.github.lupuuss.kflux.core.scope.DispatchScope
import com.github.lupuuss.kflux.thunk.Thunk

data class NormalizeEntities<State, Id : Any, Complete : Any, Normalized : Any>(
    val descriptor: EntityDescriptor<State, Id, Complete, Normalized>,
    val entities: List<Complete>
) : Thunk.Executable<Nothing> {

    override fun DispatchScope<Nothing>.execute() {
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