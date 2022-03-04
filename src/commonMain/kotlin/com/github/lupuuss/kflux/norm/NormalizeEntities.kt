package com.github.lupuuss.kflux.norm

import com.github.lupuuss.kflux.core.scope.DispatchScope
import com.github.lupuuss.kflux.thunk.Thunk

data class NormalizeEntities<State, Id : Any, Complete : Entity<Id>, Normalized : Entity<Id>>(
    val descriptor: EntityDescriptor<State, Id, Complete, Normalized>,
    val entities: List<Complete>
) : Thunk.CoreExecutable {
    override fun DispatchScope<*>.execute() {
        descriptor.storeNormalized(entities).let(::dispatch)
        entities.forEach { entity ->
            descriptor.dependencies.forEach {
                it.normalizeExtractingFrom(entity).let(::dispatch)
            }
        }
    }
}