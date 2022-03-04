package com.github.lupuuss.kflux.norm

import com.github.lupuuss.kflux.core.Action

interface EntityDescriptor<State, Id : Any, Complete : Entity<Id>, Normalized : Entity<Id>> {

    val dependencies: List<Dependency<Complete, State, *, *, *>>

    fun storeNormalized(entities: List<Complete>): Action

    fun Normalized.denormalizeUsing(state: State): Complete

    fun State.getNorm(id: Id): Normalized?

    fun State.getAllNorms(): List<Normalized>

    fun resolve(state: State, ids: List<Id>): List<Complete> = ids
        .map { state.getNorm(it) }
        .mapNotNull { it?.denormalizeUsing(state) }

    fun resolve(state: State, predicate: (Normalized) -> Boolean = { true }): List<Complete> = state
        .getAllNorms()
        .filter(predicate)
        .map { it.denormalizeUsing(state) }
}

interface PureEntityDescriptor<State, Id : Any, E : Entity<Id>> : EntityDescriptor<State, Id, E, E> {
    override val dependencies: List<Dependency<E, State, *, *, *>> get() = emptyList()
    override fun E.denormalizeUsing(state: State): E = this
}

data class Dependency<CompleteParent : Any, State, Id : Any, Complete : Entity<Id>, Normalized : Entity<Id>>(
    val descriptor: EntityDescriptor<State, Id, Complete, Normalized>,
    val extractFromParent: CompleteParent.() -> List<Complete>,
) {
    fun normalizeExtractingFrom(parent: CompleteParent) = descriptor.storeNormalized(extractFromParent(parent))
}