package com.github.lupuuss.kflux.norm

import com.github.lupuuss.kflux.core.Action

typealias AnyDependency<Complete, State> = Dependency<Complete, State, *, *, *>

interface EntityDescriptor<State, Id : Any, Complete : Any, Normalized : Any> {

    val dependencies: List<AnyDependency<Complete, State>>

    fun Complete.normalize(): Normalized

    fun Normalized.denormalize(state: State): Complete

    fun State.getNorm(id: Id): Normalized?

    fun State.getAllNorms(): List<Normalized>

    fun storeNormalized(entities: List<Normalized>): Action

    fun resolve(state: State, id: Id): Complete? = state.getNorm(id)?.denormalize(state)

    fun resolve(state: State, ids: List<Id>): List<Complete> = ids.mapNotNull { resolve(state, it) }

    fun resolve(state: State, predicate: (Normalized) -> Boolean = { true }): List<Complete> = state
        .getAllNorms()
        .filter(predicate)
        .map { it.denormalize(state) }
}

interface PureEntityDescriptor<State, Id : Any, Entity : Any> : EntityDescriptor<State, Id, Entity, Entity> {
    override val dependencies: List<AnyDependency<Entity, State>> get() = emptyList()
    override fun Entity.denormalize(state: State): Entity = this
    override fun Entity.normalize(): Entity = this
}

data class Dependency<CompleteParent : Any, State, Id : Any, Complete : Any, Normalized : Any>(
    val descriptor: EntityDescriptor<State, Id, Complete, Normalized>,
    val extractFromParent: CompleteParent.() -> List<Complete>,
) {
    fun normalizeExtractingFrom(parent: CompleteParent) = extractFromParent(parent)
        .map { descriptor.run { it.normalize() } }
        .let(descriptor::storeNormalized)
}